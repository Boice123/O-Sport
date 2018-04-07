package com.jsj141.osport.dao;
import com.jsj141.osport.domain.Shop;

import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Map;
/**
 * @author AutoGenerator
 * 
 */
@Repository
public interface ShopDao extends BaseDao {
    Shop selectByUserId(String userid);
    Shop selectByShopId(String shopid);
    List<Shop> listdesc(Map map);
    List<Shop> getAllShop();
}