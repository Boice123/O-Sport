package com.jsj141.osport.dao;

import com.jsj141.osport.domain.Diaryfirsteval;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Repository;
/**
 * @author AutoGenerator
 * 
 */
@Repository
public interface DiaryfirstevalDao extends BaseDao {
    List<Diaryfirsteval> listdesc(Map map);
}