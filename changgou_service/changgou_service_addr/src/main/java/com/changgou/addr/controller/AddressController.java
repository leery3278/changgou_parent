package com.changgou.addr.controller;

import com.changgou.addr.domain.ContactDTO;
import com.changgou.addr.domain.Kv;
import com.changgou.addr.service.AdivService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * TODO
 *
 * @Author leery
 * @Date 2021/2/3 8:56
 * @Version 1.0
 */
@Api(value = "地址识别",tags = {"地址识别"})
@RestController
@Slf4j
public class AddressController {

    @Autowired
    private AdivService adivService;

    @ApiOperation(value = "获取所有的省份")
    @GetMapping(value = "/province")
    public List<Kv> getProvinces() {
        return adivService.findAllProvince();
    }

    @ApiOperation(value = "获取所有的城市")
    @GetMapping(value = "/city")
    public List<Kv> getCitys() {
        return adivService.findAllCity();
    }

    @ApiOperation(value = "获取所有的区县")
    @GetMapping(value = "/county")
    public List<Kv> getCountys() {
        return adivService.findAllCounty();
    }

    @ApiOperation(value = "获取地区树")
    @GetMapping(value = "/tree")
    public List<Kv> getTree() {
        return adivService.getAdivTree();
    }

    @ApiOperation(value = "智能解析地址")
    @GetMapping(value = "/parse")
    public ContactDTO parseAddress(String text) {
        return adivService.parseAddress(text);
    }

}
