package com.hhu.context;

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
import org.springframework.stereotype.Component;

import com.hhu.common.Whether;
import com.hhu.dto.Accordion;
import com.hhu.entity.Function;
import com.hhu.entity.Role;
import com.hhu.entity.RoleFunction;
import com.hhu.entity.User;
import com.hhu.entity.UserRole;
import com.hhu.service.RoleService;
import com.hhu.service.UserService;

@Component
public class LoginUserHelper {

	@Autowired
	private UserService userService;
	
	@Autowired
	private RoleService roleService;
	
	@Autowired
	private NativeCache nativeCache;
	
	public void executeLogin(String userName, String pwd) {
		User user = userService.getUser(userName, pwd);
		List<UserRole> userRoles = userService.getUserRoleByUserId(user.getId());
		if(null==userRoles||0==userRoles.size()) {
			return;
		}
		List<Long> roledIds = new ArrayList<Long>();
		for(UserRole ur:userRoles) {
			roledIds.add(ur.getRole());
		}
		List<Role> roles = roleService.getRoles(roledIds);
		nativeCache.setRoles(user.getId(), roles);
		
		LoginUserCache.put(user);
		List<Accordion> accordions = getAccordions(false, user.getId());
		LoginUserCache.setAccordions(user.getName(), accordions);
	}
	
	public List<Accordion> getAccordions(boolean isAdmin, Long userId) {
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
