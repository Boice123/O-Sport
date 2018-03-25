package com.jsj141.osport.dao;

import org.springframework.stereotype.Repository;
import com.jsj141.osport.domain.Clubuseractivity;
/**
 * @author AutoGenerator
 * 
 */
@Repository
public interface ClubuseractivityDao extends BaseDao {
    Clubuseractivity getByUseridNClubactivityId(Clubuseractivity clubuseractivity);
}