package com.example.RappersInfo.demo.rapperdatabase.db;


import com.example.RappersInfo.demo.rapperdatabase.info.InstagramAccount;
import com.example.RappersInfo.demo.rapperdatabase.info.Rapper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface InstagramAccountRepository extends JpaRepository<InstagramAccount, Integer> {

    public Rapper findByName(String name);
    @Transactional
    @Modifying
    @Query("DELETE FROM InstagramAccount i WHERE i.rapper.id = :rapperId")
    void deleteByRapperId(@Param("rapperId") int rapperId);

}