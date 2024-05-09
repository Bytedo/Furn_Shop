package com.springboot.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.springboot.bean.Furn;

/*
* 如果是mybatis-plus 的mapper接口，可以通过继承BaseMapper<T> 接口
*
* */
@org.apache.ibatis.annotations.Mapper
public interface Mapper extends BaseMapper<Furn> {
    //其他方法可以在该接口声明

}
