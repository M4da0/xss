package com.madao.xss.service;


import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.jdbc.ScriptRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.charset.Charset;
import java.sql.Connection;
import java.sql.SQLException;

@Service
@Slf4j
public class InitData  {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void init() {
        log.info("初始化数据库中....");
        try {
            exceteSqlFile("generator/init.sql",jdbcTemplate.getDataSource().getConnection());
            log.info("数据库初始化完毕");
        } catch (Exception e){
            e.printStackTrace();
            log.info("执行sql脚本初始化数据库失败");
        }
    }


    public void exceteSqlFile(String filePath, Connection conn) throws IOException, SQLException, IOException {
        ScriptRunner runner = new ScriptRunner(conn);
        Resources.setCharset(Charset.forName("UTF-8"));
        runner.setLogWriter(null);//设置是否输出日志
        runner.runScript(Resources.getResourceAsReader(filePath));
        runner.closeConnection();
        conn.close();
    }
}
