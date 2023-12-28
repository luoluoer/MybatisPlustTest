package com.example.demo.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;

@Data
@TableName(value= "resultfromweb")
public class ResultFromWeb {
	@TableId(type = IdType.AUTO)
	private int id;
	private String searchdate;
	private String searchword;
	private String searchresult;
	private String hostname;
	
	public ResultFromWeb(){}
	
	public ResultFromWeb(int id,String searchdate,String searchword,String searchresult,String hostname){
		this.id = id;
		this.searchdate  = searchdate;
		this.searchword = searchword;
		this.searchresult = searchresult;
		this.hostname = hostname;
		
	}
	
}
