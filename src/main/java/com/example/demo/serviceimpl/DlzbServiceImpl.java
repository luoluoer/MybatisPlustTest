package com.example.demo.serviceimpl;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.client.UnknownHttpStatusCodeException;

import com.example.demo.Config.BaseConfig;
import com.example.demo.mapper.MineInfoMapper;
import com.example.demo.model.ResultFromWeb;
import com.example.demo.service.MineInfoService;

/*电力招标网
 * 
 * */

@Service("DdlzbServiceImpl")
//@RestController
public class DlzbServiceImpl implements MineInfoService {
	@Autowired
	MineInfoMapper mim;
	@Autowired
	@Qualifier(value = "baseConfig")
	BaseConfig b;
	@Autowired
	RestTemplate restTemplate;
    LocalDate currentdate = LocalDate.now();
    SimpleDateFormat simpledatefromate = new SimpleDateFormat("yyyy-MM-dd");
    
    String baseurl = "https://www.dlzb.com/zb/search.php?kw=";
	@Override
	public String saveRusultFromWeb() {
    	String stringcurrentdate = simpledatefromate.format(new Date());
    	List<String> word = new ArrayList<>();
    	word = b.getSearchWorddl();
    	for(int i = 0;i<word.size();i++) {
    		String responsebody = "";
    		String url = baseurl+word.get(i);
    		try {
    			ResponseEntity<String> responseentity  = restTemplate.getForEntity(url, String.class);
        		responsebody = responseentity.getBody();
        		
    		}catch(UnknownHttpStatusCodeException e){
    			System.out.println(" UnknownHttpStatusCodeException , e = " + e.getMessage());
    			ResponseEntity<String> responseentity  = restTemplate.getForEntity(url, String.class);
        		responsebody = responseentity.getBody();
        		System.out.println("在UnknownHttpStatusCodeException中重新发送一遍请求");
    		}catch (HttpClientErrorException e) {// 4xx
                System.out.print(" HttpClientErrorException , e = " + e.getMessage());
    			ResponseEntity<String> responseentity  = restTemplate.getForEntity(url, String.class);
        		responsebody = responseentity.getBody();
        		System.out.println("在HttpClientErrorException中重新发送一遍请求");                
    		}
    		//System.out.println(responsebody);
    		String regex = "\\d{4}-\\d+-\\d+";
    		String regextitle = "(?<=title=\"\">).*?(?=</a>)";
    		Pattern p = Pattern.compile(regex);
    		Matcher m = p.matcher(responsebody);
    		Pattern p1 = Pattern.compile(regextitle);
    		Matcher m1 = p1.matcher(responsebody);	
    		boolean exit = true;
    		int num = 0;
    		while (m.find() && exit == true) {
    			String stime = m.group();
    			System.out.println(stime);
    			stime.replace("-", "--");
    			
    			//对获取到的时间字符串转为date类型,然后和当前时间进行比较
    	        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");  
    	        
    	        LocalDate date = LocalDate.parse(stime, formatter);  
    	      //System.out.println(date);
    	        //获取到的时间字符串转date结束
    	        long daysBetween = ChronoUnit.DAYS.between(date, currentdate);  
    	        System.out.println("打印两个日期之间的差值====="+daysBetween);
    			if (daysBetween<8) {
    				m1.find();
    				num++;	//需要匹配到的标题的数量
    				System.out.println("打印出标题------"+m1.group());
    				String snohtml = m1.group().replaceAll("<[^>]*>", "");
    				System.out.println("打印出清除html标签的字符串"+snohtml);
    				mim.insert(new ResultFromWeb(i, stringcurrentdate, word.get(i), snohtml, baseurl));
    				
    			}else {
    				exit = false;
    			}
    	        
    		}
    		
    		
    		System.out.println(num);
    		
    		
    		
    		
    	}
		// TODO Auto-generated method stub
    	//System.out.println(currentdate);
    	
		return "852";
	}

}
