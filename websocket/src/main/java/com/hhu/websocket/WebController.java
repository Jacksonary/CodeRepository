package com.hhu.websocket;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
@RequestMapping("/")
public class WebController {
	
	@RequestMapping(value="/index", method=RequestMethod.GET)
	public String getIndex() {
		return "index";
	}

}
