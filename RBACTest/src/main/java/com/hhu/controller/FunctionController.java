package com.hhu.controller;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.hhu.common.AjaxResult;
import com.hhu.common.tree.Node;
import com.hhu.common.tree.Tree;
import com.hhu.context.NativeCache;
import com.hhu.entity.Function;
import com.hhu.service.FunctionService;

@RestController
@RequestMapping("/function")
public class FunctionController {
	
	@Autowired
	private FunctionService functionService;
	@Autowired
	private NativeCache nativeCache;
	
	@RequestMapping("/index")
	public String menuList() {
		return "/security/function_list";
	}
	
	@RequestMapping(value="/addEditFunction",method=RequestMethod.POST)
	@ResponseBody
	public AjaxResult addEditFunction(Function function) {
		if(null==function.getId()) {
			function.setSerialNum(nativeCache.getFunctions().size());;
			functionService.addFunction(function);
			nativeCache.addFunction(function);
		} else {
			functionService.updateUrl(function.getId(), function.getUrl());
			nativeCache.replaceFunction(function);
		}
		return AjaxResult.success();
	}
	
	@RequestMapping(value="/deleteFunction",method=RequestMethod.POST)
	@ResponseBody
	public AjaxResult deleteFunctionById(Long id) {
		functionService.deleteFunctionById(id);
		nativeCache.removeFunction(id);
		return AjaxResult.success();
	}
	
	@RequestMapping(value="/getSubFunctions",method=RequestMethod.POST)
	@ResponseBody
	public List<Function> getSubFunctions(Integer page, Integer rows, Long parentId) {
		if(Objects.equals(null, parentId)) {
			parentId = 0L;
		}
		return functionService.getFunctions(page, rows, parentId);
	}
	
	/*
	 * 构建用于新建、修改使用的菜单树
	 */
	@RequestMapping(value="/buildFunctionTreeForEdit",method=RequestMethod.POST)
	@ResponseBody
	public List<Node> buildMenuTreeForEdit() {
		List<Function> list = nativeCache.getFunctions();
		Tree tree = new Tree(list);
		return tree.build();
	}

}
