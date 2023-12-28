package com.example.demo.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.mapper.UserTestMapper;
import com.example.demo.model.UserTest;
import com.example.demo.service.UserTestService;
@Service
public class UserTestServiceImpl implements UserTestService {
@Autowired
UserTestMapper utm;
	@Override
	public String addUserInfo() {
		UserTest u1 = new UserTest(1,"cfg","male");
		// TODO Auto-generated method stub
		utm.insert(u1);
		return "success";
	}

}
