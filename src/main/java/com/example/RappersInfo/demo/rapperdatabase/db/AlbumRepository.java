package com.example.RappersInfo.demo.rapperdatabase.db;

import com.example.RappersInfo.demo.rapperdatabase.info.Album;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlbumRepository extends JpaRepository<Album, Integer> {

    public Album findByName(String name);


}