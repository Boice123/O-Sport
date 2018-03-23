package com.jsj141.osport.dao;

import org.springframework.stereotype.Repository;
import java.util.List;
import com.jsj141.osport.domain.Triptime;
/**
 * @author AutoGenerator
 * 
 */
@Repository
public interface TriptimeDao extends BaseDao {
    List<Triptime> selectList(Triptime triptime);
    void deleteByTripid(String tripid);
    void updateTriptimemaxpeople(Triptime triptime);
}