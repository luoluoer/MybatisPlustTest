package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.serviceimpl.UserTestServiceImpl;

@RestController
public class IndexController {
	@Autowired
	UserTestServiceImpl usi;
	@GetMapping("/")
	public String index() {
		usi.addUserInfo();
		return "aaa";
	}
}
