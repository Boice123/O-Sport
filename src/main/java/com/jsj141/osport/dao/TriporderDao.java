package com.jsj141.osport.dao;

import org.springframework.stereotype.Repository;

import com.jsj141.osport.domain.Triporder;
import java.util.List;
/**
 * @author AutoGenerator
 * 
 */
@Repository
public interface TriporderDao extends BaseDao {
    List<Triporder> selectByShopid(String shopid);
    List<Triporder> selectByUserid(String userid);
}