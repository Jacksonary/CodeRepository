package com.hhu.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hhu.common.Whether;
import com.hhu.context.LoginUserCache;
import com.hhu.context.NativeCache;
import com.hhu.context.UserContext;
import com.hhu.dto.Accordion;
import com.hhu.entity.Function;
import com.hhu.entity.Role;
import com.hhu.entity.RoleFunction;
import com.hhu.entity.User;
import com.hhu.entity.UserRole;
import com.hhu.service.RoleService;
import com.hhu.service.UserService;

@RestController
public class LoginController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private NativeCache nativeCache;
	
	@Autowired
	private RoleService roleService;

	@RequestMapping(value="/auth",method=RequestMethod.GET)
	public String index() {
		if(null!=UserContext.getCurrent()&&null!=UserContext.getCurrent().getUser()) {
			return "/layout/main";
		}
		return "/security/login";
	}
	
	@RequestMapping(value="/login",method=RequestMethod.GET)
	public String login(Model model, String name, String pwd) {
		User user = userService.getUser(name, pwd);
		if(user==null) {
			return "/security/login";
		}
		
		try {
			//设置缓存时间为30分钟,这种缓存方式是有问题的，舍去
//			LoginUserCache.put(user, 30*60);
			
			if(Objects.equals("admin", user.getName())) {
				model.addAttribute("accordions", getAccordions(true,null));
			} else {
				//查询用户所具备的角色信息
				List<UserRole> userRoles = userService.getUserRoleByUserId(user.getId());
				
				//用户不具备任何角色信息,则返回登陆页面
				if(userRoles==null||userRoles.size()==0) {
					return "/security/login";
				}
				List<Long> roles = new ArrayList<Long>();
				for(UserRole ur:userRoles) {
					roles.add(ur.getRole());
				}
				List<Role> roless = roleService.getRoles(roles);
				nativeCache.setRoles(user.getId(), roless);
				
				//修改缓存的地方
				LoginUserCache.put(user);
				List<Accordion> accordions = getAccordions(false, user.getId());
				//将菜单信息同样和缓存到cookie中
				LoginUserCache.setAccordions(user.getName(),accordions);
				
				model.addAttribute("accordions", accordions);
			}
			return "/layout/main";
		} catch (Exception e) {
			// 移除用户信息
			LoginUserCache.remove(user.getName());
			return "/security/login";
		}
		
	}
	
	@RequestMapping(value="/logout",method=RequestMethod.POST)
	public String logout() {
		//从上下文中清除用户的权限信息
		if(null!=UserContext.getCurrent()&&null!=UserContext.getCurrent().getUser()) {
			LoginUserCache.remove(UserContext.getCurrent().getUser().getName());
		}
		return "/security/login";
	}
	
	private List<Accordion> getAccordions(boolean isAdmin, Long userId) {
		Set<String> permissionUrls = new HashSet<>();
		Set<Long> rootFunctionIdSet = new HashSet<>();
		if(!isAdmin) {//非管理员身份,根据角色查询每个Function
			for(Role role:nativeCache.getRoles(userId)) {
				List<RoleFunction> roleFunctions = roleService.getRolesFunctions(role.getId());
				for(RoleFunction rf:roleFunctions) {
					Function f = nativeCache.getFunction(rf.getFunctionId());
					if(Objects.equals(f.getAccordiont(), Whether.YES.getValue())) {
						rootFunctionIdSet.add(f.getId());
					} else {
						permissionUrls.add(f.getUrl());
					}
				}
			}
		}
		Map<Long, Accordion> accordionMap = new HashMap<Long, Accordion>();
		List<Accordion> permissionAccordionSet = new LinkedList<>();
		
		List<Function> functions = nativeCache.getFunctions();
		
		List<Accordion> rootAccordionSet = new LinkedList<>();
		for(Function f:functions) {
			Accordion accordion = new Accordion(f.getId(), f.getParentId(), f.getName(), f.getUrl(), f.getSerialNum());
			accordionMap.put(f.getId(), accordion);
			if(!isAdmin) {
				if(permissionUrls.contains(f.getUrl())) {
					permissionAccordionSet.add(accordion);
				}
				if(rootFunctionIdSet.contains(f.getId())) {
					rootAccordionSet.add(accordion);
				}
			} else {
				permissionAccordionSet.add(accordion);
				if(Whether.YES.getValue()==f.getAccordiont()) {
					rootAccordionSet.add(accordion);
				}
			}
		}
		Collections.sort(rootAccordionSet);
		Collections.sort(permissionAccordionSet);
		for(Accordion a:rootAccordionSet) {
			completeAccordion(permissionAccordionSet,a);
		}
		return rootAccordionSet;
	}
	
	private void completeAccordion(List<Accordion> permissionAccordionSet, Accordion rootAccordion) {
		for(Accordion a:permissionAccordionSet) {
			if(Objects.equals(a.getParentId(), rootAccordion.getId())) {
				rootAccordion.getChildren().add(a);
			}
		}
	}
	
}
