package com.example.demo.serviceimpl;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.print.attribute.Size2DSyntax;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.example.demo.Config.BaseConfig;
import com.example.demo.mapper.MineInfoMapper;
import com.example.demo.model.ResultFromWeb;
import com.example.demo.service.MineInfoService;
/*
 * 国家能源招标网
 * */
@Service("CnenergyBiddingServiceImpl")
public class CnenergyBiddingServiceImpl implements MineInfoService {
	@Resource
    private RestTemplate restTemplate;
	@Autowired
	MineInfoMapper mim;
	@Autowired
	@Qualifier(value = "baseConfig")
	BaseConfig b2;
	String url = "http://www.chnenergybidding.com.cn/bidfulltextsearch/rest/inteligentSearch/getFullTextData";

	//headers.setContentLength(392);
	//log.info("这里是JSON处理后的JSON数据-------"+json);
    HttpEntity<Map<String, Object>> httpEntity = null;
    //@GetMapping("/333") 
	@Override
	public String saveRusultFromWeb() {
		// TODO Auto-generated method stub
		List<String> word = b2.getSearchWord();
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		Date currentdate  = new Date();
		SimpleDateFormat timeformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Calendar calendar = Calendar.getInstance();
	    calendar.setTime(currentdate);
	    calendar.add(Calendar.DAY_OF_MONTH, -7);
	    Date newTime = calendar.getTime();
		//存放当前日期字符串
		String formattedDate = timeformat.format(newTime);
		String searchdate = timeformat.format(currentdate);
		Map<String, Object> requestBody = new HashMap<>();
		for(int i = 0;i<word.size();i++) {//遍历查询关键字循环
			
			requestBody.put("token","");
			requestBody.put("pn", 0);
			requestBody.put("rn", "10");
			requestBody.put("sdt", formattedDate);
			requestBody.put("edt", searchdate);
			requestBody.put("wd", word.get(i));
			requestBody.put("inc_wd", "");
			requestBody.put("exc_wd", "");
			requestBody.put("fields", "title;content");
			requestBody.put("cnum", "");
			//这个sort虽然是正确的,但是,导致servlet报错,不能发送post请求,尼玛蛋.尝试了半天......改成空值,
			//先凑合用,把请求发了吧
			//requestBody.put("sort", "{\\\"infodate\\\":0}");
			requestBody.put("sort", "");
			requestBody.put("cl", "500");
			requestBody.put("terminal", "");
			requestBody.put("condition", null);
			requestBody.put("time", null);
			requestBody.put("highlights", "title;content");
			requestBody.put("statistics", null);
			requestBody.put("unionCondition", null);
			requestBody.put("accuracy", "");
			requestBody.put("noParticiple", "0");
			requestBody.put("searchRange", null);
			requestBody.put("ssort","title");
			httpEntity = new HttpEntity<>(requestBody,headers);
			System.out.println("there is httpEntity------"+httpEntity);
			ResponseEntity<String> responseBody = restTemplate.postForEntity(url, httpEntity, String.class);
			System.out.println("this is responseBody------"+responseBody);
			System.out.println("这里打印出返回的字符串-------"+responseBody.getBody());
			String bodyString = responseBody.getBody();
			JSONObject jsonobject = new JSONObject(bodyString);
			Object Jresult = jsonobject.get("result");
			Object Jnum = ((JSONObject)Jresult).get("totalcount");
			//System.out.println(Jnum.getClass());
			//System.out.println(Jnum);
			//通过计算Jnum,处理翻页,网站默认10条数据进行翻页

			@SuppressWarnings("removal")
			double d = new Double(((Integer)Jnum).intValue());
			System.out.println(d);
			int num = (int)(Math.ceil(d/10));
			System.out.println(num);
			for(int k = 0;k<num;k++) {
				System.out.println("翻页遍历开始");
				System.out.println(num);
				//翻页参数 ,存储到requestBody中的pn是控制翻页的参数,0对应第一页,10对应第二页,类推
				requestBody.put("token","");
				requestBody.put("pn", k*10);
				requestBody.put("rn", "10");
				requestBody.put("sdt", formattedDate);
				requestBody.put("edt", searchdate);
				requestBody.put("wd", word.get(i));
				requestBody.put("inc_wd", "");
				requestBody.put("exc_wd", "");
				requestBody.put("fields", "title;content");
				requestBody.put("cnum", "");
				//这个sort虽然是正确的,但是,导致servlet报错,不能发送post请求,尼玛蛋.尝试了半天......改成空值,
				//先凑合用,把请求发了吧
				//requestBody.put("sort", "{\\\"infodate\\\":0}");
				requestBody.put("sort", "");
				requestBody.put("cl", "500");
				requestBody.put("terminal", "");
				requestBody.put("condition", null);
				requestBody.put("time", null);
				requestBody.put("highlights", "title;content");
				requestBody.put("statistics", null);
				requestBody.put("unionCondition", null);
				requestBody.put("accuracy", "");
				requestBody.put("noParticiple", "0");
				requestBody.put("searchRange", null);
				requestBody.put("ssort","title");
				httpEntity = new HttpEntity<>(requestBody,headers);
				System.out.println("这里输出第"+k+"次请求的请求体"+httpEntity);
				ResponseEntity<String> responseBody1 = restTemplate.postForEntity(url, httpEntity, String.class);
				System.out.println("this is responseBody------"+responseBody1);
				
				bodyString = responseBody1.getBody();
				System.out.println("这里打印出返回的字符串-------"+bodyString);
				jsonobject = new JSONObject(bodyString);
				System.out.println("翻页遍历结果-----"+k);
				System.out.println(jsonobject);
				Jresult = jsonobject.get("result");
				Object Jrecords = ((JSONObject)Jresult).get("records");
				System.out.println("这里打印Jrecords长度------"+((JSONArray)Jrecords).length());
				//遍历JSONarray取出title开始
				for (int j = 0;j<((JSONArray)Jrecords).length();j++) {
					Object titleObject = ((JSONObject)((JSONArray)Jrecords).get(j)).get("title");
					System.out.println("this is your title-----"+titleObject);
					String Stringtitle = titleObject.toString();
					String regex = "<[\\s\\S]*?>";
					String titlenoem = Stringtitle.replaceAll(regex,"");
					System.out.println(titlenoem);
					ResultFromWeb r1 = new ResultFromWeb(j, searchdate, word.get(i), titlenoem,"http://www.chnenergybidding.com.cn/bidweb/");
					mim.insert(r1);
				}
				//遍历JSONarray取出title结束
				//翻页遍历结束
				
			}
			
			
			
			
			
			
		}
		
		
		
		
		return "99999";
	}

}
