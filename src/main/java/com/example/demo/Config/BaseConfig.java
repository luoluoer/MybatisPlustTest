package com.example.demo.Config;

import java.util.Arrays;
import java.util.List;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
@Configuration
public class BaseConfig {
	
	int a;
	List<String> searchword =  Arrays.asList("监测","矿压","顶板","预警","冲击地压",
			"应力监测","矿压大数据","灾害预警平台","顶板离层","岩层控制");
	List<String> searchworddl =  Arrays.asList("矿压","顶板","冲击地压",
			"应力监测","矿压大数据","灾害预警平台","顶板离层","岩层控制");
	//List <String>searchword  =Arrays.asList("顶板");
	//String[] searchwordtest = {"石拉乌素","矿压","冲击地压","顶板","应力监测","预警","矿压大数据","灾害预警平台","顶板离层","岩层控制"};
	//矿压，冲击地压，顶板，应力监测，预警，矿压大数据，灾害预警平台，顶板离层，岩层控制
	public BaseConfig(){
		//String[] searchwordtest = {"石拉乌素","矿压","冲击地压","顶板","应力监测","预警","矿压大数据","灾害预警平台","顶板离层","岩层控制"};
		
		
	}
	public  List<String> getSearchWord(){
		return searchword;
	}
	
	public  List<String> getSearchWorddl(){
		return searchworddl;
	}
}
