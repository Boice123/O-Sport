package com.jsj141.osport.dao;

import org.springframework.stereotype.Repository;

import com.jsj141.osport.domain.Triporder;
import java.util.List;
import java.util.Map;
/**
 * @author AutoGenerator
 * 
 */
@Repository
public interface TriporderDao extends BaseDao {
//    List<Triporder> selectByShopid(String shopid);
//    List<Triporder> listdescbytripid(Map map);
    List<Triporder> listdesc(Map map);
    int countbytripid(Map map);
 }