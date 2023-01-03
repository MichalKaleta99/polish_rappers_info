package com.example.RappersInfo.demo.rapperdatabase.db;


import com.example.RappersInfo.demo.rapperdatabase.info.Album;
import com.example.RappersInfo.demo.rapperdatabase.info.InstagramAccount;
import com.example.RappersInfo.demo.rapperdatabase.info.Rapper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RapperRepository extends JpaRepository<Rapper, Integer> {

    public Rapper findByName(String name);


    @Query(value = "SELECT * FROM rapper", nativeQuery = true)
    List<Rapper> findAllRappers();

    @Query("SELECT i FROM InstagramAccount i WHERE i.rapper.id = :id")
    InstagramAccount findInstagramById(int id);

    @Query("SELECT i FROM Album i WHERE i.rapper.id = :id")
    List<Album> findAlbumsByRapersId(int id);


}