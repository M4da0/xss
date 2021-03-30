package com.madao.xss.controller;



import com.madao.xss.mapper.ReciverDao;
import com.madao.xss.pojo.Reciver;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@Slf4j
@RestController
@RequestMapping("/")
@CrossOrigin
public class ReciverController {


    @Autowired
    private ReciverDao reciverDao;


    @GetMapping("/api/all")
    public List<Reciver> getAllRecivers() {
        return reciverDao.getRecivers();
    }


    @GetMapping("/api/del")
    public int delReciver(@RequestParam("time") String time) {
        return reciverDao.delReciver(time);
    }

}
