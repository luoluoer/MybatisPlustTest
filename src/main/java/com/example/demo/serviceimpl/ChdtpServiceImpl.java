package com.example.demo.serviceimpl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
import org.springframework.web.servlet.function.ServerRequest.Headers;

import com.example.demo.Config.BaseConfig;
import com.example.demo.mapper.MineInfoMapper;
import com.example.demo.model.ResultFromWeb;
import com.example.demo.service.MineInfoService;
/*
 * 华电电子商务平台
 * 
 * */
@Service("ChdtpServiceImpl")
public class ChdtpServiceImpl implements MineInfoService {
	@Autowired
	@Qualifier(value = "baseConfig")
	BaseConfig b2;
	@Autowired
	MineInfoMapper mim;
	@Autowired
	RestTemplate resttemplate;
	HttpHeaders headers = new HttpHeaders();
	HttpEntity<MultiValueMap<String,Object>> entity = null;
	String baseurl = "https://www.chdtp.com.cn/webs/queryWebZbgg.action";
	Date currentdate = new Date();
	SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
	String formattedDate = dateformat.format(currentdate);
	@Override
	
	public String saveRusultFromWeb() {
		// TODO Auto-generated method stub
		//取出搜索关键词用于遍历
		List<String> word = b2.getSearchWord();
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
		MultiValueMap<String,Object> requestbody = new  LinkedMultiValueMap();
		//第一次请求是为了获取搜索结果总数,所以requestbody中只存放了搜索关键词和key
		requestbody.add("key", 1);
		for(int i = 0;i<word.size();i++) {
			int num = 0;
			//requestbody.put("search", word.get(i));	
			requestbody.add("search", word.get(i));
			entity = new HttpEntity<>(requestbody,headers);
			// ResponseEntity<String> responsebody = resttemplate.postForEntity(baseurl, entity, String.class);
			String responsebody = resttemplate.postForEntity(baseurl, entity, String.class).getBody();
			System.out.println(responsebody);
			//问了下迟菲,说是搜索的结果不会超过一页,后面不想折腾了.不进行循环处理了,就按照一页的结果来处理....
			//处理思路,通过正则表达式,将公告状态,公告标题,发布日期,分别存入三个List中,然后依次进行判断
			/*1.先判断发布状态,如果公告状态是正在发售,然后判断发布日期是否是七天以内,如果都满足,那么就取出标题,存入数据库
			 * 
			 * */
			//判断正在发售的额数量开始
			String regexstatus = "正在发售";
			String regextitle = "(?<=title=\").*?(?=\")";
			String regexdate = "\\d+-\\d+-\\d+";
			Pattern p = Pattern.compile(regexstatus);
			Matcher m  = p.matcher(responsebody);
			Pattern p1 = Pattern.compile(regextitle);
			Matcher m1  = p1.matcher(responsebody);
			Pattern p2 = Pattern.compile(regexdate);
			Matcher m2  = p2.matcher(responsebody);
			while (m.find()) {
				num++;
			}
			//判断正在发售的额数量结束
			//根据正在发售的数量,使用正则表达式,获取标题,这里没有判断发布时间,如果有问题的话,后期再进行修改,开始
			for(int j = 0;j<num;j++) {
				m1.find();
				m2.find();
				String searchresult = m1.group()+m2.group();
//				public ResultFromWeb(int id,String searchdate,String searchword,String searchresult,String hostname)
				ResultFromWeb r1 = new ResultFromWeb(i,formattedDate,word.get(i),searchresult,"https://www.chdtp.com.cn/webs/searchAction.action");
				
				//根据正在发售的数量,使用正则表达式,获取标题,这里没有判断发布时间,如果有问题的话,后期再进行修改,结束
				//System.out.println("这里输出获取到的标题---------"+m1.group());
				mim.insert(r1);
			}
//			for(int j = 0;j<num;j++) {
//				m2.find();
//				//System.out.println("这里输出获取到的时间---------"+m2.group());
//			}
			
		}
		
		
		return "6666";
	}

}
