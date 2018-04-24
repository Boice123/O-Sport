package com.jsj141.osport.dao;

import com.jsj141.osport.domain.Diarysecondeval;
import java.util.List;
import org.springframework.stereotype.Repository;
/**
 * @author AutoGenerator
 * 
 */
@Repository
public interface DiarysecondevalDao extends BaseDao {
    List<Diarysecondeval> getByFirstevalid(String firstevalid);
}