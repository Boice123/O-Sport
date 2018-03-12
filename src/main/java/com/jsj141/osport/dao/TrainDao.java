package com.jsj141.osport.dao;

import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Map;

import com.jsj141.osport.domain.Train;
/**
 * @author AutoGenerator
 * 
 */
@Repository
public interface TrainDao extends BaseDao {
    List<Train> selectTrainByShopId(String shopId);
    int getCountByShopId(String shopid);
    List<Train> listdesc(Map map);
}