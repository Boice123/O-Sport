package com.jsj141.osport.base;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * 用来读取application.properties的配置
 * 以下示例中显示获取tomcat的端口号配置
 */
@Configuration
public class BaseConfig {

    @Value("${server.port}")
    private int port;

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }
}

