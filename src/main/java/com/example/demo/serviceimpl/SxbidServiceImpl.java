package com.example.demo.serviceimpl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;

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
@Service("SxbidServiceImpl")
public class SxbidServiceImpl  implements MineInfoService{
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
	public String saveRusultFromWeb() {
		//存放搜索关键字
		List<String> word = b3.getSearchWord();
		Date currentdate  = new Date();
		SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
		//存放当前日期字符串	
		String searchdate = dateformat.format(currentdate);
		// TODO Auto-generated method stub
		String totalnum = "0";
		String baseurl  = "https://www.sxbid.com.cn/f/new/search/searchList";
		HttpHeaders headers = new HttpHeaders();
		headers.set(HttpHeaders.CONTENT_TYPE,MediaType.APPLICATION_FORM_URLENCODED_VALUE);
		MultiValueMap<String ,Object> requestbody = new LinkedMultiValueMap<String, Object>();
		HttpEntity<MultiValueMap<String,Object> >httpentity = null;
		requestbody.add("pageNo", 1);
		requestbody.add("pageSize", "15");
		requestbody.add("searchField", "title");
		//requestbody.add("searchContent", "顶板");这个应该在循环中处理,因此先屏蔽掉
		requestbody.add("recentType", "7");
		requestbody.add("startDate", "");
		requestbody.add("endDate", "");
		requestbody.add("releaseType", "");
		requestbody.add("projectPalce", "");
		requestbody.add("industryClassification", "");
		requestbody.add("cId", "");
//		requestbody.remove("pageNo");
//		requestbody.add("pageNo", 2);
		//开始循环关键字
		for(int k = 0;k<word.size();k++) {
			requestbody.remove("searchContent");
			requestbody.add("searchContent", word.get(k));//在插入前先删除,避免重复
			//System.out.println(requestbody);
			httpentity = new HttpEntity<>(requestbody,headers);
			ResponseEntity<String> responseBody = restTemplate.postForEntity(baseurl, httpentity, String.class);
			//System.out.println("responseBody-------"+responseBody);
			String regex0  = "(?<=共有<strong> )\\d+(?= </strong>条信息)";//共有<strong> 1 </strong>条信息,总条数
			String regex1 = "(?<=td class=\"text_center\">)\\d+-\\d+-\\d+";//日期
			//target="_blank">
			String regex2 = "(?<=target=\"_blank\">).*?(?=</a>)";//标题
			Pattern p0 = Pattern.compile(regex0);
			Matcher m0 = p0.matcher(responseBody.toString());
			System.out.println("有没有匹配到啊,大哥0!");
			while(m0.find()) {
				//System.out.println(m0.group());
				//listtitle.add(m0.group());
				totalnum= m0.group();//这里赋值的是所有的条数,查询到后,再通过ceil获取到翻页次数
				
			}
			//处理翻页次数开始
			@SuppressWarnings("removal")
			double d = new Double(Integer.valueOf(totalnum));
			//System.out.println(d);
			int num = (int)(Math.ceil(d/15));
			//System.out.println(num);
			//处理翻页次数结束
			//开始进行翻页请求,
			for(int i= 0;i<num;i++) {
				requestbody.remove("pageNo");
				requestbody.add("pageNo",i+1);
				httpentity = new HttpEntity<>(requestbody,headers);
				responseBody = restTemplate.postForEntity(baseurl, httpentity, String.class);
				//System.out.println("responseBody-------"+responseBody);
				
				
				//responseBody = restTemplate.postForEntity(baseurl, httpentity, String.class);
				
				
//				Pattern p1 = Pattern.compile(regex1);
//				Matcher m1 = p1.matcher(responseBody.toString());
//				System.out.println("有没有匹配到啊,大哥1!");
//				while(m1.find()) {
//					listdate.add(m1.group());
//					
//					//System.out.println(m1.group());
//					
//				}
				//System.out.println("this is listtdate------"+listdate);
				Pattern p2 = Pattern.compile(regex2);
				Matcher m2 = p2.matcher(responseBody.toString());
				//System.out.println("有没有匹配到啊,大哥2");
				while(m2.find()) {
					//System.out.println("处理前的m2----"+m2.group());
					String mstring = m2.group();
					mstring = mstring.replace("&rdquo;", "”");
					mstring = mstring.replace("&ldquo;", "“");
					//System.out.println("处理后的m2----"+mstring);
					//listtitle.add(mstring);
					ResultFromWeb r1 = new ResultFromWeb(i, searchdate, word.get(k), mstring,"https://www.sxbid.com.cn/");
					mim.insert(r1);
//			        try {  
//			            // 等待0.5秒  
//			            Thread.sleep(500);  
//			        } catch (InterruptedException e) {  
//			            e.printStackTrace();  
//			        }  
				}		
				//System.out.println("this is listtdate------"+listtitle);
				
				//System.out.println(listdate.size());
				//System.out.println(listtitle.size());
				
			}
			
		}
		//结束循环关键字
		
		

		
		//结束翻页请求
		//min.insert(null);
		
		
		
		
		
		

		return "success963";
	}

}
