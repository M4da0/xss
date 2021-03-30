package com.madao.xss.mapper;

import com.madao.xss.pojo.Reciver;

import java.util.List;

public interface ReciverDao {

    int insertSelective(Reciver record);

    List<Reciver> getRecivers();

    int delReciver(String time);
}