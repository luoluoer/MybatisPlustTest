package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.service.MineInfoService;
import com.example.demo.serviceimpl.MineGoodsServiceImpl;

@RestController
/*
 * 中国矿用物资网查找
 * */
public class MineGoods {
	@Autowired
	@Qualifier("MineGoodsServiceImpl")
	MineInfoService sx;
	@GetMapping("/minegoods")
	@Scheduled(cron = "0 55 9 * * ?")
	public String addMineGoodsSearchInfo() {
		sx.saveRusultFromWeb();
		return "success";
		
	}
}
