package com.jsj141.osport.dao;

import com.jsj141.osport.domain.Evaluate;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Map;
/**
 * @author AutoGenerator
 * 
 */
@Repository
public interface EvaluateDao extends BaseDao {
    List<Evaluate> listdesc(Map map);
}