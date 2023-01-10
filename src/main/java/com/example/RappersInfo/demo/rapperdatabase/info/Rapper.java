package com.example.RappersInfo.demo.rapperdatabase.info;

import lombok.*;


import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Table(name="rapper")

public class Rapper {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
    private LocalDate dateOfBirth;

    @ToString.Exclude
    @OneToMany(mappedBy="rapper")
    private Set<Album> albums;

}
