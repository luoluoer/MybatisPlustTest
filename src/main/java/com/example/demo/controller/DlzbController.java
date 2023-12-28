
package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.service.MineInfoService;
import com.example.demo.serviceimpl.MineGoodsServiceImpl;

@RestController
/*
 * 电力招标网
 * */
public class DlzbController {
	@Autowired
	@Qualifier("DdlzbServiceImpl")
	MineInfoService sx;
	@GetMapping("/dlzb")
	public String addMineGoodsSearchInfo() {
		sx.saveRusultFromWeb();
		return "success";
		
	}
}