package com.jsj141.osport.dao;

import org.springframework.stereotype.Repository;
import java.util.List;

import com.jsj141.osport.domain.Trip;
/**
 * @author AutoGenerator
 * 
 */
@Repository
public interface TripDao extends BaseDao {
    List<Trip> selectTripByShopId(String shopId);
}