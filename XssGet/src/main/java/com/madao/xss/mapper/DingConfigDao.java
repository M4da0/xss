package com.madao.xss.mapper;

import com.madao.xss.pojo.DingConfig;

public interface DingConfigDao {

    DingConfig getConfig();

    int updateConfig(DingConfig record);
}