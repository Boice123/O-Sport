package com.jsj141.osport.service;

import com.jsj141.osport.config.Constant;
import com.jsj141.osport.domain.Admin;
import com.iw86.base.Row;
import org.apache.commons.text.RandomStringGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * 管理员对应的服务，目前只有dao层的使用示例
 */
@Service
public class AdminService {
    private final Logger logger = LoggerFactory.getLogger(AdminService.class);

    private RandomStringGenerator stringGenerator = new RandomStringGenerator.Builder().withinRange('0', '9').withinRange('A', 'Z').withinRange('a', 'z').build();

//    public List<Admin> list(int page, int size) {
//        Row row = new Row();
//        row.put("start", (page - 1) * size);
//        row.put("size", size);
//        List<Admin> list = Constant.FACADE.getAdminDao().list(row);
//        for (Admin admin : list) {
//            admin.setPassword("");
//        }
//        return list;
//    }

    public int count() {
        Row row = new Row();
        return Constant.FACADE.getAdminDao().count(row);
    }

    public Admin select(int id) {
        return (Admin) Constant.FACADE.getAdminDao().select(id);
    }

    public void delete(Integer id) {
        Constant.FACADE.getAdminDao().delete(id);
    }
}
