
package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.service.MineInfoService;
import com.example.demo.serviceimpl.MineGoodsServiceImpl;

@RestController
/*
 * 晋能控股
 * */
@EnableScheduling
public class JnkgController {
	@Autowired
	@Qualifier("JnkgServiceImpl")
	MineInfoService sx;
	@GetMapping("/jnkg")
	@Scheduled(cron = "0 58 9 * * ?")
	public String addMineGoodsSearchInfo() {
		sx.saveRusultFromWeb();
		return "success";
		
	}
}