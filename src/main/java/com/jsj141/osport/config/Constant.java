package com.jsj141.osport.config;

import com.jsj141.osport.SpringContext;
import org.springframework.context.ApplicationContext;

/**
 * 根据需要自行添加常量配置
 * 这里对dao层使用了门面模式，才用springcontext来获取beanFactory
 */
public class Constant {
    public static ApplicationContext BF = null;

    public static Facade FACADE;

    // 最大数量
    public static final int MAXNUM = 1000000;

    static {
        if (BF == null) {
            BF = SpringContext.getApplicationContext();
            if (BF != null && FACADE == null) {
                FACADE = (Facade) BF.getBean("facade");
            }
        }
    }
}
