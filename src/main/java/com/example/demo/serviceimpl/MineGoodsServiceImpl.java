package com.example.demo.serviceimpl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.demo.Config.BaseConfig;
import com.example.demo.mapper.MineInfoMapper;
import com.example.demo.model.ResultFromWeb;

import com.example.demo.service.MineInfoService;

import javax.annotation.Resource;
/*
 * 中国矿用物资网,山能招标
 * 
 * */
@Service("MineGoodsServiceImpl")
public class MineGoodsServiceImpl extends BaseConfig implements MineInfoService {
	@Resource
    private RestTemplate restTemplate;
	@Autowired
	MineInfoMapper mim;
	@Autowired
	BaseConfig b;
	@Override
	public String saveRusultFromWeb() {
		//BaseConfig b = new BaseConfig();
		//搜索关键字获取
		List<String> word = b.getSearchWord();
		// TODO Auto-generated method stub
		Date currentdate  = new Date();
		SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
		//存放当前日期字符串	
		String searchdate = dateformat.format(currentdate);
		Calendar calendar = Calendar.getInstance();
	    calendar.setTime(currentdate);
	    calendar.add(Calendar.DAY_OF_MONTH, -6);
	    Date newTime = calendar.getTime();
	    String formattedDate = dateformat.format(newTime);
		//存放查询结果实体对象,用于写入到数据库
		List<ResultFromWeb> SearchBiddingInfoentity = new  ArrayList<>();
		//存放正则表达式根据关键字截取出的标题
		List <String> matchs = new ArrayList<>();
		//全文返回文本html代码字符串
		String content = "";
		//关键字匹配字符串
		String matchString = "";
		//获取页码角标字符串
		String pagenum = "0";
		//拼接字符串用的基础url
		String realbaseurl = "http://snzb.minegoods.com/sdnycms/category/bulletinList.html?searchDate="+
				formattedDate
				+"&dates=7"
				+"&categoryId=2&tabName=&industryName=&status="
				;
		//请求url基本链接,没有拼接关键字和页码信息,这个是测试用的,
		String BaseUrl = "http://snzb.minegoods.com/sdnycms/category/bulletinList.html?searchDate=1998-11-10&dates=300&categoryId=2&tabName=&industryName=&status=";
		//url全部时间
		String url = "http://snzb.minegoods.com/sdnycms/category/bulletinList.html?searchDate=1998-11-10&dates=300"
				+ "&word=哈哈哈哈哈&categoryId=2&tabName=&industryName=&status=";
		//url七天
		//String url = "https://snzb.minegoods.com/sdnycms/category/bulletinList.html?searchDate=2023-11-07&dates=7&word=石拉乌素&categoryId=2&tabName=&industryName=&status=";
		
		
		
		//处理多个不同关键字的请求,开始
		for(int  k = 0;k<word.size();k++) {
			System.out.println("这里打印的是请求的url");
			String url1 = realbaseurl+"&word="+word.get(k);
			System.out.println(url1);
			ResponseEntity<String> result = restTemplate.getForEntity(url1, String.class);
			//请求结果的返回体
			content = result.getBody();
			 //处理页码循环开始
	        Pattern pagenumP = Pattern.compile("(?<=共<label>)\\d+");
	        Matcher matcherPagenum = pagenumP.matcher(content);
	        while (matcherPagenum.find()) {
	        	System.out.println("发现页码时打印的返回体"+content);
	        	System.out.println("发现了页码");
	        	pagenum  = matcherPagenum.group(0);
	        	System.out.println("this is your pagenum " + pagenum);
	        }
	        //处理页码循环结束
	        
	      //开始循环翻页功能
	        for (int i = 1;i<Integer.valueOf(pagenum)+1 ;i++) {
	        	
	        	result = restTemplate.getForEntity(url1, String.class);
	        	System.out.println("这是第"+i+"次请求");
	    		content = result.getBody();
	    		System.out.println("这是第"+i+"次请求的返回值\n"+content);
	            System.out.println(result.getStatusCode());
	            //System.out.println(result.getBody());
	            System.out.println(result.getHeaders());
	          Pattern pattern = Pattern.compile("(?<=title=\").*?(?=\")");
	          Matcher matcher = pattern.matcher(content);
	          System.out.println("this is matcher"+matcher);
	          while (matcher.find()) {
	          	System.out.println("faxianle ");
	          	matchString = matcher.group();
	          	ResultFromWeb r1 = new ResultFromWeb(i, searchdate, word.get(k), matchString,"http://snzb.minegoods.com/");
	          	mim.insert(r1);
	          	matchs.add(matchString);
	          	System.out.println(matchString);
	          }
	          System.out.println(  "this is ayyaylist----"+matchs);
	        	
	        }
	        //结束循环翻页功能
		//处理多个不同关键字的请求,结束
		

		}
		return null;
	}
	
}
