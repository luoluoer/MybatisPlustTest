package com.example.demo.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.model.ResultFromWeb;

@Mapper
public interface MineInfoMapper extends BaseMapper<ResultFromWeb>{
	
}
