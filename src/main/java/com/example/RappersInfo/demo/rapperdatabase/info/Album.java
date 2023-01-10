package com.example.RappersInfo.demo.rapperdatabase.info;

import javax.persistence.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name="album")

public class Album {

    @Id
    @GeneratedValue
    private int id;
    @NonNull
    private String name;
    @NonNull

    private String lengthOfAlbum;
    @NonNull

    private int numberOfSongs;

    @ManyToOne
    @JoinColumn(name = "rapper_id")
    private Rapper rapper;

}
