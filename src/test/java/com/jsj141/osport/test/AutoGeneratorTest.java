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

//        auto.create("User", "user", "com.jsj141.osport", "resources/mybatis-mapper");
//        auto.create("triptime", "triptime", "com.jsj141.osport", "resources/mybatis-mapper");
//        auto.create("shop", "shop", "com.jsj141.osport", "resources/mybatis-mapper");
//        auto.create("trip", "trip", "com.jsj141.osport", "resources/mybatis-mapper");
//        auto.create("train", "train", "com.jsj141.osport", "resources/mybatis-mapper");
//        auto.create("triporderitem", "triporderitem", "com.jsj141.osport", "resources/mybatis-mapper");
//        auto.create("triporder", "triporder", "com.jsj141.osport", "resources/mybatis-mapper");
//        auto.create("club", "club", "com.jsj141.osport", "resources/mybatis-mapper");
//        auto.create("clubuseritem", "clubuseritem", "com.jsj141.osport", "resources/mybatis-mapper");
//        auto.create("clubdiary", "clubdiary", "com.jsj141.osport", "resources/mybatis-mapper");
//        auto.create("clubactivity", "clubactivity", "com.jsj141.osport", "resources/mybatis-mapper");
//        auto.create("clubuseractivity", "clubuseractivity", "com.jsj141.osport", "resources/mybatis-mapper");

    }
}
