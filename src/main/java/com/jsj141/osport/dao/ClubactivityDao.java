package com.jsj141.osport.dao;

import org.springframework.stereotype.Repository;
import com.jsj141.osport.domain.Clubactivity;
import java.util.List;
import java.util.Map;
/**
 * @author AutoGenerator
 * 
 */
@Repository
public interface ClubactivityDao extends BaseDao {
    List<Clubactivity> getByClubid (Clubactivity clubactivity);
    void updatePeople(String clubactivityid);
    List<Clubactivity> listdesc(Map map);
}