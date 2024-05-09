package com.springboot;

import com.springboot.bean.Furn;
import com.springboot.mapper.Mapper;
import com.springboot.service.FurnService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;

@SpringBootTest
public class ApplicationTest {
    @Resource
    private Mapper furnMapper;

    //装配Service
    @Resource
    private FurnService furnService;

    @Test
    public void testFurnMaooer() {
        System.out.println(furnMapper.getClass());
        Furn furn = furnMapper.selectById(4);
        System.out.println(furn);
    }

    @Test
    public void testFurnService(){
        List<Furn> furns = furnService.list();
        for (Furn furn : furns){
            System.out.println(furn);
        }
    }

}


