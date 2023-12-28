package com.example.demo;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.client.RestTemplate;

import lombok.extern.slf4j.Slf4j;
@Slf4j
@SpringBootApplication
@EnableScheduling
public class MybatisPlustTestApplication {

	public static void main(String[] args) {
		SpringApplication.run(MybatisPlustTestApplication.class, args);
	}
	@Bean
	RestTemplate testTemplate () {
		System.out.println("你好");
		Date currentdate  = new Date();
		SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
		String formattedDate = dateformat.format(currentdate);
		System.out.println("输出当前时间为:   "+formattedDate);
		return new RestTemplate();
	}
	}