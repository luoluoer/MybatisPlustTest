package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Conditional;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.service.MineInfoService;
import com.example.demo.serviceimpl.SxbidServiceImpl;
@RestController
public class SxjmController {

	
	
	@Autowired
	SxbidServiceImpl simpl;
	@Autowired
	@Qualifier("SxjmServiceImpl")
	MineInfoService sx;
	
//	@Resource
//	private RestTemplate restTemplate;
	@GetMapping("/sxjm")
	@Scheduled(cron = "0 50 9 * * ?")
	public String addMineGoodsSearchInfo() {
		sx.saveRusultFromWeb();
		
		return "succes3333s444";
	}
}
