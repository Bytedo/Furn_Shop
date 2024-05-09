package com.springboot.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.springboot.bean.Furn;
import com.springboot.service.FurnService;
import com.springboot.util.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;


@Slf4j
@RestController
public class ProController {


    @Resource
    private FurnService furnService;

    //编写方法，完成添加
    @PostMapping("/save")
    public Result save(@Validated @RequestBody Furn furn, Errors errors) {
        HashMap<String, Object> map = new HashMap<>();
        List<FieldError> fieldErrors = errors.getFieldErrors();
        //遍历，将错误信息放入到map中
        for (FieldError fieldError : fieldErrors) {
            map.put(fieldError.getField(), fieldError.getDefaultMessage());
        }
        if (map.isEmpty()) {
            log.info("接收到请求，参数：{}", furn);
            furnService.save(furn);
            return Result.success();
        } else {
            return Result.error("400", "后端校验失败", map);
        }
    }

    @PutMapping(value = "/update")
    @ResponseBody
    public Result update(@RequestBody Furn furn) {
        log.info("接收到请求，参数：{}", furn);
        furnService.updateById(furn);
        return Result.success();
    }

    //查询商品,根据id 查询商品信息
    @GetMapping("/find/{id}")
    public Result findByID(@PathVariable Integer id) {
        Furn furn = furnService.getById(id);
        log.info("furn={}", furn);
        return Result.success(furn); //返回成功的信息-携带查询 furn 信息
    }

    //删除商品
    @DeleteMapping("/delete/{id}")
    @ResponseBody
    public Result<?> del(@PathVariable Integer id) {
        furnService.removeById(id);
        return Result.success();
    }

    //分页查询的接口
    @ResponseBody
    @RequestMapping("/listFurnsByPage")
    public Result<?> listFurnsByPage(@RequestParam(defaultValue = "1") Integer pageNum, //默认显示第一页
                                     @RequestParam(defaultValue = "5") Integer pageSize) { //每页显示几个，默认5个
        Page<Furn> page = furnService.page(new Page<>(pageNum, pageSize));
        return Result.success(page);
    }

    //支持带条件的的分页检索
    @RequestMapping("/furnsByPage")
    public Result listFurnsByPage(@RequestParam(defaultValue = "1") Integer pageNum, //默认显示第一页
                                  @RequestParam(defaultValue = "5") Integer pageSize,
                                  @RequestParam(defaultValue = "") String name,
                                  @RequestParam(defaultValue = "") String maker) {
        QueryWrapper<Furn> queryWrapper = Wrappers.query();
        if (!StringUtils.isEmpty(name)) {
            queryWrapper.like("name", name);
        }
        if (!StringUtils.isEmpty(maker)) {
        }
        return null;
    }

    //查询功能
    @ResponseBody
    @RequestMapping("/furnsBySearchPage")
    public Result<?> listFurnsByConditionPage(@RequestParam(defaultValue = "1") Integer pageNum,
                                              @RequestParam(defaultValue = "5") Integer pageSize,
                                              @RequestParam(defaultValue = "") String search) {
        //创建LambdaQueryWrapper 封装查询跳进
        QueryWrapper<Furn> queryWrapper = Wrappers.query();
        if (StringUtils.hasText(search)) {
            queryWrapper.like("name", search);
        }
        Page<Furn> page =
                furnService.page(new Page<>( pageNum, pageSize), queryWrapper);
        return Result.success(page);
    }

}
