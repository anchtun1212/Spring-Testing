package com.anchtun.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.anchtun.service.NameService;

import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
public class HelloController {

	private final NameService nameService;

	@GetMapping
	@ResponseBody
	public String sayHello() {
		return "Hello " + nameService.getName();
	}

}
