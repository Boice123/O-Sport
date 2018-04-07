package com.jsj141.osport.dao;

import org.springframework.stereotype.Repository;
import com.jsj141.osport.domain.Admin;
/**
 * @author AutoGenerator
 * 
 */
@Repository
public interface AdminDao extends BaseDao {
    Admin selectByTel(Admin admin);
}