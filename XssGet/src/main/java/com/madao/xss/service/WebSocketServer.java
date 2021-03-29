package com.madao.xss.service;


import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@ServerEndpoint(value = "/websocket")
@Component
public class WebSocketServer {

//  与某个客户端的连接会话，借助其向对应的客户端发送消息
    private Session session;
    private String sessionId;

//    静态 map,存放每个客户端对应的 socket
    private static Map<String,WebSocketServer> clents = new ConcurrentHashMap<String, WebSocketServer>();


//    连接建立成功调用
    @OnOpen
    public void onOpen(Session session) {
        //每个session 表示一个client
        this.session = session;

        Date date = new Date();
        this.sessionId = date.toString();
        clents.put(sessionId,this);
        log.info("连接建立成功");
    }

//    连接关闭调用
    @OnClose
    public void onClose() {
        log.info("关闭了一个连接");
        clents.remove(this.sessionId);
    }

//    收到客户端消息调用
    @OnMessage
    public void onMessage(String message, Session session) {
        System.out.println("来自客户端的消息："+message);
    }

//    连接建立错误
    @OnError
    public void onError(Session session, Throwable error) {
        System.out.println("发生错误");
        error.printStackTrace();
    }

//    向客户端推送信息
    public static void sendMessage(String message) throws IOException {
        for (WebSocketServer socketServer : clents.values())
            socketServer.session.getBasicRemote().sendText(message);
    }

}
