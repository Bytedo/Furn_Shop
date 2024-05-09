package com.springboot.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.springboot.bean.Furn;
import com.springboot.mapper.Mapper;
import com.springboot.service.FurnService;
import org.springframework.stereotype.Service;

@Service
public class FurnServiceImpl extends ServiceImpl<Mapper, Furn> implements FurnService {
}
