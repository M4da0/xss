package com.madao.xss.controller;

import com.madao.xss.mapper.PayloadsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import sun.misc.BASE64Decoder;


import java.io.IOException;

@ResponseBody
@RequestMapping("/api")
@Controller
public class GetJscodeController {

    @Autowired
    private PayloadsMapper payloadsMapper;

    @RequestMapping (value = "/z/{name}",produces = "application/javascript;charset=UTF-8")
    public String getJscode(@PathVariable("name") String name) throws IOException {

        //数据库中取 js 代码的 base64
        String data = payloadsMapper.getJscode(name);


        //base64 解码 得到 payload
        BASE64Decoder decoder = new BASE64Decoder();
        byte[] bytes = decoder.decodeBuffer(data);
        String jsCode = new String(bytes,"utf-8");

        return jsCode;
    }

}
