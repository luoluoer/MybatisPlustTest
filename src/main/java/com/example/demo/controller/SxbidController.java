package com.example.demo.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.example.demo.service.MineInfoService;
import com.example.demo.serviceimpl.SxbidServiceImpl;

import java.util.regex.Matcher;
/*
 * 请求体用LinkedMultiValueMap,请求头
 * headers.set(HttpHeaders.CONTENT_TYPE,MediaType.APPLICATION_FORM_URLENCODED_VALUE);
 * */
@RestController
public class SxbidController {
//	ArrayList<String> listtitle  = new ArrayList<>();
//	ArrayList<String> listtdate  = new ArrayList<>();
	@Autowired
	SxbidServiceImpl simpl;
	@Autowired
	@Qualifier("SxbidServiceImpl")
	MineInfoService sx;
	
//	@Resource
//	private RestTemplate restTemplate;
	@GetMapping("/sxbid")
	public String addMineGoodsSearchInfo() {
		sx.saveRusultFromWeb();
		
//		String totalnum = "0";
//		String baseurl  = "https://www.sxbid.com.cn/f/new/search/searchList";
//		HttpHeaders headers = new HttpHeaders();
//		headers.set(HttpHeaders.CONTENT_TYPE,MediaType.APPLICATION_FORM_URLENCODED_VALUE);
//		MultiValueMap<String ,Object> requestbody = new LinkedMultiValueMap<String, Object>();
//		HttpEntity<MultiValueMap<String,Object> >httpentity = null;
//		requestbody.add("pageNo", 1);
//		requestbody.add("pageSize", "");
//		requestbody.add("searchField", "title");
//		requestbody.add("searchContent", "顶板");
//		requestbody.add("recentType", "");
//		requestbody.add("startDate", "");
//		requestbody.add("endDate", "");
//		requestbody.add("releaseType", "");
//		requestbody.add("projectPalce", "");
//		requestbody.add("industryClassification", "");
//		requestbody.add("cId", "");
//		requestbody.remove("pageNo");
//		requestbody.add("pageNo", 2);
//		System.out.println(requestbody);
//		httpentity = new HttpEntity<>(requestbody,headers);
//		ResponseEntity<String> responseBody = restTemplate.postForEntity(baseurl, httpentity, String.class);
//		System.out.println("responseBody-------"+responseBody);
//		String regex0  = "(?<=共有<strong> )\\d+(?= </strong>条信息)";//共有<strong> 1 </strong>条信息
//		String regex1 = "(?<=td class=\"text_center\">)\\d+-\\d+-\\d+";
//		//target="_blank">
//		String regex2 = "(?<=target=\"_blank\">).*?(?=</a>)";
//		Pattern p0 = Pattern.compile(regex0);
//		Matcher m0 = p0.matcher(responseBody.toString());
//		System.out.println("有没有匹配到啊,大哥0!");
//		while(m0.find()) {
//			System.out.println(m0.group());
//			//listtitle.add(m0.group());
//			totalnum= m0.group();
//			
//		}
//		Pattern p1 = Pattern.compile(regex1);
//		Matcher m1 = p1.matcher(responseBody.toString());
//		System.out.println("有没有匹配到啊,大哥1!");
//		while(m1.find()) {
//			listtdate.add(m1.group());
//			
//			System.out.println(m1.group());
//			
//		}
//		System.out.println("this is listtdate------"+listtdate);
//		Pattern p2 = Pattern.compile(regex2);
//		Matcher m2 = p2.matcher(responseBody.toString());
//		System.out.println("有没有匹配到啊,大哥2");
//		while(m2.find()) {
//			System.out.println("处理前的m2----"+m2.group());
//			String mstring = m2.group();
//			mstring = mstring.replace("&rdquo;", "”");
//			mstring = mstring.replace("&ldquo;", "“");
//			System.out.println("处理后的m2----"+mstring);
//			listtitle.add(mstring);
//		}		
//		System.out.println("this is listtdate------"+listtitle);
//		@SuppressWarnings("removal")
//		double d = new Double(Integer.valueOf(totalnum));
//		System.out.println(d);
//		int num = (int)(Math.ceil(d/15));
//		System.out.println(num);
		return "success444";
	}
}
