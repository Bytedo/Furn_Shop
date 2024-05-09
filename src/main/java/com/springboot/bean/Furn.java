package com.springboot.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@TableName("furn")
public class Furn {

    @TableId(type = IdType.AUTO)
    private Integer id;
    @NotEmpty(message = "名称不能为空")
    private String name;
    @NotEmpty(message = "厂商不能为空")
    private String maker;
    @NotNull(message = "价格不能为空")
    @Range(min = 0, message = "价格不能为0")
    private Double price;
    @NotNull(message = "销量不能为空")
    @Range(min = 0, message = "销量不能为0")
    private Integer sales;
    @NotNull(message = "库存不能为空")
    @Range(min = 0, message = "库存不能为0")
    private Integer stock;
}
