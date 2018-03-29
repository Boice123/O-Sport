package com.jsj141.osport.dao;

import org.springframework.stereotype.Repository;
import com.jsj141.osport.domain.Club;
import java.util.List;
import java.util.Map;
/**
 * @author AutoGenerator
 * 
 */
@Repository
public interface ClubDao extends BaseDao {
    Club selectByClubowner(Club club);
    List<Club> selectByUserid(String userid);
    List<Club> selectAll();
    List<Club> selectByClubtab(Club club);
    void updateClubPeople(String clubid);
    List<Club> listdesc(Map map);
    List<Club> searchKey(Map map);
}