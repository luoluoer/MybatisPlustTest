package com.example.demo.serviceimpl;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.example.demo.mapper.MineInfoMapper;
import com.example.demo.model.ResultFromWeb;
import com.example.demo.service.MineInfoService;
@Service("SxjmServiceImpl")
public class SxjmServiceImpl implements MineInfoService {
	@Autowired
	MineInfoMapper mim;
	@Resource
	RestTemplate  resttemplate;
	String baseurl = "http://www.sxccdzzcpt.cn/cms/channel/3ywgg1/index.htm";
	LocalDate currentdate = LocalDate.now();
	SimpleDateFormat simpledatefromate = new SimpleDateFormat("yyyy-MM-dd");
	boolean flag = true;
	String pagenum = "1";
	 @Override
	@GetMapping("/sxjm")
	public String saveRusultFromWeb() {
		// TODO Auto-generated method stub
		String stringcurrentdate = simpledatefromate.format(new Date());
		while (flag) {
			String url = baseurl+"?pageNo="+pagenum;
			ResponseEntity<String> responseentity = resttemplate.getForEntity(url, String.class);
			String responsebody = responseentity.getBody();
			System.out.println(responsebody);
//			String regexdate = "(<em>)//d+-//d+-//d+";
			//String regexdate = "(?<=<em>)\\d+-\\d+-\\d+";
			String regexdate = "[0-9]{4}-\\d+-\\d+";
			String regextitle = "(?<=</i>).*?(?=</span>)";
			Pattern p1 = Pattern.compile(regexdate);
			Matcher m1 = p1.matcher(responsebody);
			Pattern p2 = Pattern.compile(regextitle);
			Matcher m2 = p2.matcher(responsebody);
			System.out.println("执行到了这里");
			while (m1.find()) {
				String datestring = m1.group();
				System.out.println("输出datestring------"+datestring);
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");  
		        
		        LocalDate date = LocalDate.parse(datestring, formatter); 
		        long daysBetween = ChronoUnit.DAYS.between(date, currentdate);  
		        if(daysBetween<8) {
		        	System.out.println(datestring);
		        	m2.find();
		        	String stringtitle = m2.group();
					System.out.println(stringtitle);
					mim.insert(new ResultFromWeb(0, stringcurrentdate, "无法根据关键字搜索", stringtitle, url) );
		        }else {
		        	flag = false;
		        	break;
		        	}
				
				
			}
			
			Integer i =  Integer.parseInt(pagenum)+1;
			pagenum = i.toString();
		}
		
		
		return "99999";
	}

}
