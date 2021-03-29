package com.madao.xss.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface InitDao {

    @Select("SHOW TABLES LIKE 'xssmadao'")
    public String isInit();

}
