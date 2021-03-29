package com.madao.xss.controller;

import com.madao.xss.mapper.DingConfigDao;
import com.madao.xss.pojo.DingConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class DingConfigController {

    @Autowired
    private DingConfigDao dingConfigDao;

    //获取配置
    @GetMapping("/get/config")
    DingConfig getDingConfig() {
        return dingConfigDao.getConfig();
    }

    //修改配置
    @PostMapping("/set/config")
    int setDingConfig(@RequestBody DingConfig dingConfig) {
        return dingConfigDao.updateConfig(dingConfig);
    }
}
