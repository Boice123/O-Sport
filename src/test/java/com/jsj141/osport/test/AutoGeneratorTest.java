package com.jsj141.osport.test;

import com.jsj141.osport.config.Constant;
import com.jsj141.osport.util.AutoGenerator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.sql.DataSource;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AutoGeneratorTest {

    @Test
    public void testGenerate() throws Exception {
        DataSource ds = (DataSource) Constant.BF.getBean("dataSource");
        AutoGenerator auto = new AutoGenerator(ds);

//        auto.create("admins", "admins", "com.jsj141.osport", "resources/mybatis-mapper");
    }
}
