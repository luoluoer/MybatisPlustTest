package com.example.demo.controller;
import org.apache.catalina.valves.JsonAccessLogValve;
import org.json.*;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.example.demo.service.MineInfoService;
import com.example.demo.serviceimpl.CnenergyBiddingServiceImpl;

import lombok.extern.slf4j.Slf4j;

/*
 * 国能招标网
 * requestbody用map
 * */
@RestController
@Slf4j
public class ChnenergyBiddingController {
	@Autowired
	@Qualifier("CnenergyBiddingServiceImpl")
	MineInfoService sx;
	@GetMapping("/chnenergybidding")
	@Scheduled(cron = "0 0 10 * * ?")
	public String addMineGoodsSearchInfo() {
		sx.saveRusultFromWeb();
		return "999";
	}
	

}
	//	@Resource
//    private RestTemplate restTemplate;
//	@GetMapping("/chnenergybiddinginfo")
//	public String addChnenergyBiddingInfo() {
//		
//		String url = "http://www.chnenergybidding.com.cn/bidfulltextsearch/rest/inteligentSearch/getFullTextData";
//		HttpHeaders headers = new HttpHeaders();
//		//headers.setContentType(MediaType.parseMediaType("application/json;charset=UTF-8")); 
//		headers.setContentType(MediaType.APPLICATION_JSON);
//		//headers.setContentLength(392);
//		//log.info("这里是JSON处理后的JSON数据-------"+json);
//        HttpEntity<Map<String, Object>> httpEntity = null;
//		//log.info("这里是拼接的requestBody------"+requestBody);
//        
//		Map<String, Object> requestBody = new HashMap<>();
//
//		requestBody.put("token","1");
//		requestBody.put("pn", "0");
//		requestBody.put("rn", "10");
//		requestBody.put("sdt", "2023-11-14 10:59:53");
//		requestBody.put("edt", "2023-11-21 18:59:53");
//		requestBody.put("wd", "黄白茨");
//		requestBody.put("inc_wd", "");
//		requestBody.put("exc_wd", "");
//		requestBody.put("fields", "title;content");
//		requestBody.put("cnum", "");
//		//这个sort虽然是正确的,但是,导致servlet报错,不能发送post请求,尼玛蛋.尝试了半天......改成空值,
//		//先凑合用,把请求发了吧
//		//requestBody.put("sort", "{\\\"infodate\\\":0}");
//		requestBody.put("sort", "");
//		requestBody.put("cl", "500");
//		requestBody.put("terminal", "");
//		requestBody.put("condition", null);
//		requestBody.put("time", null);
//		requestBody.put("highlights", "title;content");
//		requestBody.put("statistics", null);
//		requestBody.put("unionCondition", null);
//		requestBody.put("accuracy", "");
//		requestBody.put("noParticiple", "0");
//		requestBody.put("searchRange", null);
//		requestBody.put("ssort","title");
//		httpEntity = new HttpEntity<>(requestBody,headers);
//		System.out.println("there is httpEntity------"+httpEntity);
//		ResponseEntity<String> responseBody = restTemplate.postForEntity(url, httpEntity, String.class);
//		System.out.println("this is responseBody------"+responseBody);
//		
//		//log.info(responseBody);
//		String  bodystring = responseBody.getBody();
//		System.out.println("这里打印出返回的字符串-------"+responseBody.getBody());
//		
//		JSONObject obj = new JSONObject(bodystring);
//		System.out.println("输出body_json-------"+obj);
//		Object  j = obj.get("result");
//		//System.out.println(obj.get("result"));
////		JSONObject JSONObject_records = new JSONObject(
////				obj.get("result")
////				);
//		System.out.println("this is j----"+j);
//		System.out.println("JSONObject_records------"+j.getClass());
//		Object t = ((JSONObject)j).get("records");
//		
//		System.out.println("this is t --------"+t);
//		//System.out.println(t.getClass());
//		
//		//Object real = ((JSONObject)t).get(bodystring);
//		for(int i = 0;i<4;i++) {
//			System.out.println("这是jsonarray的输出"+((JSONArray)t).get(i));
//			Object e = ((JSONObject)((JSONArray)t).get(i)).get("title");
//			System.out.println("这是JSON字符串重新解析的输出-------"+ e         );//((JSONArray)t).get(i)
//		}
//		
//		return "success2";
	//}
	
	
	
//	  private static HttpHeaders setHeaders() {
//	        HttpHeaders httpHeaders = new HttpHeaders();
//	        httpHeaders.add("Accept", MediaType.APPLICATION_JSON.toString());
//	        httpHeaders.add("Accept-Charset", "UTF-8");
//	        return httpHeaders;
//	    }
//	  
	  
//	   public static JSONObject httpPostResult(String url, requestBody<String, Object> reqParam, requestBody<String, Object> reqBody) {
	  
//	        String params = getParameter(reqParam);
//	        if (StringUtils.isBlank(params)) {
//	            log.error("获取sign失败,sign=null");
//	            return null;
//	        }
//
//
//	
//}

//
//MultiValuerequestBody<String, Object> requestBody = new LinkedMultiValuerequestBody<>();
//requestBody.add("token","1");
//requestBody.add("pn", "0");
//requestBody.add("rn", "10");
//requestBody.add("sdt", "2023-11-11 10:59:53");
//requestBody.add("edt", "2023-11-18 10:59:53");
//requestBody.add("wd", "黄白茨");
//requestBody.add("inc_wd", "");
//requestBody.add("exc_wd", "");
//requestBody.add("fields", "title;content");
//requestBody.add("cnum", "");
//requestBody.add("sort", "{\\\"infodate\\\":0}");
//requestBody.add("cl", "500");
//requestBody.add("terminal", "");
//requestBody.add("condition", null);
//requestBody.add("time", null);
//requestBody.add("highlights", "title;content");
//requestBody.add("statistics", null);
//requestBody.add("unionCondition", null);
//requestBody.add("accuracy", "");
//requestBody.add("noParticiple", "0");
//requestBody.add("searchRange", null);
//requestBody.add("ssort","title");