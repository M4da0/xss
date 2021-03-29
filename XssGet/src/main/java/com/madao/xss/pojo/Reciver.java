package com.madao.xss.pojo;

import java.io.Serializable;
import lombok.Data;

/**
 * reciver
 * @author 
 */
@Data
public class Reciver implements Serializable {
    private String host;

    private String connection;

    private String userAgent;

    private String accept;

    private String acceptEncoding;

    private String cookie;

    @Override
    public String toString() {
        return  "host='" + host + '\'' +
                ", \n\nconnection='" + connection + '\'' +
                ", \n\nuserAgent='" + userAgent + '\'' +
                ", \n\naccept='" + accept + '\'' +
                ", \n\nacceptEncoding='" + acceptEncoding + '\'' +
                ", \n\ncookie='" + cookie + '\'' +
                ", \n\ntime='" + time + '\'' +
                ", \n\ndata='" + data + '\'' +
                ", \n\nip='" + ip + '\'';
    }

    private String time;

    private String data;

    private String ip;

    private static final long serialVersionUID = 1L;
}