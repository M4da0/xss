package com.madao.xss.controller;


import com.madao.xss.mapper.PayloadsMapper;
import com.madao.xss.pojo.Payloads;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@CrossOrigin
@RequestMapping("/api")
public class PayloadController {

    @Autowired
    private PayloadsMapper payloadsMapper;


    //获取 payloads
    @GetMapping("/payloads")
    public List<Payloads> getPayloads() {
        return  payloadsMapper.getAllPayloads();
    }

    //收藏
    @GetMapping("/love")
    public int updateLove(@RequestParam("name") String name,@RequestParam("love") int love){
        return payloadsMapper.updateLove(name,love);
    }

    //上传
    @PostMapping("/add")
    public int addPayloads(@RequestBody Payloads payloads) {
        return payloadsMapper.addPayloads(payloads);
    }


    //获取我的 payloads
    @GetMapping("/mine")
    public List<Payloads> getMyPayloads(){
        return payloadsMapper.getMyPayloads();
    }



    //    删除    /delete/filename
    @GetMapping("/delete/{name}")
    public int deletePayloads(@PathVariable("name") String name) {
        return payloadsMapper.deletePayloads(name);
    }

    //       /update?name=xx&code=xx
    //修改 payloads
    @GetMapping("/update")
    public int updateLove(@RequestParam("name") String name,@RequestParam("code") String code){
        return payloadsMapper.updateJscode(name,code);
    }

}
