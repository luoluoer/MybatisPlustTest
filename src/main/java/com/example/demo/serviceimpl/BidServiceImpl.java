package com.example.demo.serviceimpl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.example.demo.Config.BaseConfig;
import com.example.demo.mapper.MineInfoMapper;
import com.example.demo.model.ResultFromWeb;
import com.example.demo.service.MineInfoService;
/*
 * 山西招标网
 * 
 * */
//@Service("BidServiceImpl")
@RestController
public class BidServiceImpl  implements MineInfoService{
	ArrayList<String> listtitle  = new ArrayList<>();
	ArrayList<String> listdate  = new ArrayList<>();
	@Resource
	private RestTemplate restTemplate;
	@Autowired
	MineInfoMapper mim;
	@Autowired
	@Qualifier(value = "baseConfig")
	BaseConfig b3;
	@Override
	@GetMapping("/biddddd")
	public String saveRusultFromWeb() {
		//存放搜索关键字
		List<String> word = b3.getSearchWord();
		Date currentdate  = new Date();
		SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
		//存放当前日期字符串	
		String searchdate = dateformat.format(currentdate);
		// TODO Auto-generated method stub
		String totalnum = "0";
		//
		String baseurl  = "http://bid.xkjt.net/bid_ol/bulletinWeb/getTenderBulletinList.do?purchase=2&keyword=";
		HttpHeaders headers = new HttpHeaders();
		headers.set(HttpHeaders.CONTENT_TYPE,MediaType.APPLICATION_FORM_URLENCODED_VALUE);
//		headers.set(, baseurl);
		MultiValueMap<String ,Object> requestbody = new LinkedMultiValueMap<String, Object>();
		HttpEntity<MultiValueMap<String,Object> >httpentity = null;
		requestbody.add("sortDesc", 2);
		requestbody.add("pageIndex", 1);
		//pageSize
		requestbody.add("pageSize",11);
		//开始循环关键字
		for(int k = 0;k<1;k++) {
			//requestbody.remove("searchContent");
			//requestbody.add("searchContent", word.get(k));//在插入前先删除,避免重复
			//System.out.println(requestbody);
//			String url = baseurl+word.get(k);
			String url = baseurl+"检测";
			httpentity = new HttpEntity<>(requestbody,headers);
			System.out.println(url);
			System.out.println(httpentity);
			ResponseEntity<String> responseentity = restTemplate.postForEntity(url, httpentity, String.class);
			String responsebody = responseentity.getBody();
			System.out.println(responsebody);
			JSONObject returnObject =  new JSONObject(responsebody);
			System.out.println(returnObject);
			System.out.println(returnObject.get("list"));
			JSONArray j = (JSONArray) returnObject.get("list");
			
			for(int i= 0;i<j.length();i++) {
				JSONObject t = (JSONObject) j.get(i);
				String bulletinName = t.get("bulletinName").toString();
				String bulletinIssueTime = t.get("bulletinIssueTime").toString();
				SimpleDateFormat formated = new SimpleDateFormat("yyyy-MM-dd"); 
				Date d = null;
				try {
					d = formated.parse(bulletinIssueTime);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
//				Date d = (Date) bulletinIssueTime;
				System.out.println("d===== "+d);
				System.out.println("currentdate===== "+currentdate.getTime());
				System.out.println("d.gettime==     ------"+d.getTime());
				System.out.println("currentdate.gettime==     ------"+currentdate.getTime());
				//System.out.println("时间差为"+String.valueOf((double)(currentdate.getTime()-d.getTime())/1000)/60/60/24));
				int c = (int) (currentdate.getTime()-d.getTime()/1000/60/60/24);
				System.out.println(c);
				if (c<7) {
					ResultFromWeb r1 = new ResultFromWeb(i, searchdate, word.get(k), bulletinName,baseurl);
					mim.insert(r1);
				}else if (c>7){
					break;
				}
			}
			
			
			
		
		
		
		

		
	}
		return "success963";
	}

}
