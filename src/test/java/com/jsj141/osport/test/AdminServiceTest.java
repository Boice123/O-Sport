package com.jsj141.osport.test;


import com.jsj141.osport.domain.Admin;
import com.jsj141.osport.service.AdminService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.Assert;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AdminServiceTest {
    @Autowired
    AdminService adminService;

    @Test
    public void selectTest() {
        Admin admin = adminService.select(1);
        Assert.isTrue(admin != null);
    }
}
