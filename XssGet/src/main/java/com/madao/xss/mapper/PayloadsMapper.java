package com.madao.xss.mapper;


import com.madao.xss.pojo.Payloads;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface PayloadsMapper {

    //获取所有payloads
    @Select("SELECT * FROM payloads")
    List<Payloads> getAllPayloads();

    //上传文件
    @Insert("INSERT INTO payloads VALUES( #{name}, #{desc}, #{jscode}, #{love})")
    int addPayloads(Payloads payloads);

    //收藏
    @Update("UPDATE payloads SET love = #{love} WHERE name= #{name}")
    int updateLove(@Param("name") String name,@Param("love") int love);

    //获取我的 payloads
    @Select("SELECT * FROM payloads WHERE love = 1")
    List<Payloads> getMyPayloads();

    //获取Jscode
    @Select("SELECT jscode FROM payloads WHERE name = #{name}")
    String getJscode(@Param("name") String name);

    //删除
    @Delete("DELETE FROM payloads WHERE name = #{name}")
    int deletePayloads(@Param("name") String name);

    //修改
    @Update("UPDATE payloads SET jscode = #{jscode} WHERE name = #{name}")
    int updateJscode(@Param("name") String name,@Param("jscode") String jscode);
}
