package com.example.RappersInfo.demo.rapperdatabase.db;

import com.example.RappersInfo.demo.rapperdatabase.info.Rapper;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class RapperRepositoryImpl  {

    @PersistenceContext
    private EntityManager entityManager;

    public List<Rapper> findAll() {
        String sql = "SELECT * FROM rapper";
        Query query = entityManager.createNativeQuery(sql, Rapper.class);
        return query.getResultList();
    }
}