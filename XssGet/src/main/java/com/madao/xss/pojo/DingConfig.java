package com.madao.xss.pojo;

import java.io.Serializable;
import lombok.Data;

/**
 * dingconfig
 * @author 
 */
@Data
public class DingConfig implements Serializable {
    private String webhook;

    private boolean isnotice;

    private Integer msgconfig;

    private static final long serialVersionUID = 1L;
}