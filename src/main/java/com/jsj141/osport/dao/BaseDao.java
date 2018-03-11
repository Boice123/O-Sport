package com.jsj141.osport.dao;

import java.util.List;
import java.util.Map;

/**
 * Created by lixue on 06/11/2017.
 */
public interface BaseDao {
    int insert(Object obj);

    Object select(Object obj);

    int update(Object obj);

    int delete(Object obj);

    int count(Map map);

    List list(Map map);

    List search(Map map);

}
