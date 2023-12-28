package com.example.demo.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;

@Data
@TableName(value = "usertest")
public class UserTest {
	public UserTest(int i, String name, String sex) {
		// TODO Auto-generated constructor stub
		this.id = id;
		this.name = name;
		this.sex = sex;
	}
	@TableId(type=IdType.AUTO)
	private int id;
	private String name;
	private String sex;
}
