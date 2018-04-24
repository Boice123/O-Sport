package com.jsj141.osport.dao;

import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Map;

import com.jsj141.osport.domain.Trip;
/**
 * @author AutoGenerator
 * 
 */
@Repository
public interface TripDao extends BaseDao {
    List<Trip> listdesc(Map map);
    List<Trip> listdescclose(Map map);
    int countClose(Map map);
    void updateTripTrading(Trip trip);
    List<Trip> searchKey(Map map);
}