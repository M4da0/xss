package com.madao.xss.function;


import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Component
public class DingDing {
    private String webhook;

    public void dingPost(String body, String webhook) {

        this.webhook = webhook;

        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity httpEntity = new HttpEntity(body,headers);

        try {
            ResponseEntity<String> strbody = restTemplate.exchange(webhook, HttpMethod.POST,httpEntity,String.class);
            //JSONObject jsonObject = JSONObject.parseObject(strbody.getBody());
            //log.info(jsonObject.toJSONString());

        } catch (Exception e) {
            log.info(e.toString());
        }

    }
}
