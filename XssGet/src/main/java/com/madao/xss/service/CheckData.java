package com.madao.xss.service;

import com.madao.xss.mapper.InitDao;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class CheckData implements ApplicationRunner {

    @Autowired
    private InitDao initDao;

    @Autowired
    private InitData initData;

    @Override
    public void run(ApplicationArguments args) throws Exception {

        try {
            String zz = initDao.isInit();
            if(zz!=null && zz.equals("xssmadao")) {
                log.info("存在表，已经初始化");
            }else {
                log.info("未初始化");
                initData.init();
            }

        } catch (Exception e){
            log.info("初始化检查时，数据库连接出错");
        }

    }
}
