package com.jsj141.osport.dao;

import org.springframework.stereotype.Repository;
import com.jsj141.osport.domain.Clubuseritem;
/**
 * @author AutoGenerator
 * 
 */
@Repository
public interface ClubuseritemDao extends BaseDao {
    Clubuseritem selectByUseridNClubid(Clubuseritem clubuseritem);
}