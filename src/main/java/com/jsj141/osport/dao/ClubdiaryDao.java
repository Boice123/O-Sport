package com.jsj141.osport.dao;

import org.springframework.stereotype.Repository;
import com.jsj141.osport.domain.Clubdiary;
import java.util.List;
/**
 * @author AutoGenerator
 * 
 */
@Repository
public interface ClubdiaryDao extends BaseDao {
    List<Clubdiary> getByClubid(Clubdiary clubdiary);
    List<Clubdiary> getByClubowner(String clubowner);
    List<Clubdiary> getByUserid(String userid);
}