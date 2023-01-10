package com.example.RappersInfo.demo.rapperdatabase.info;

import javax.persistence.*;

import lombok.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Table(name="label")

public class Label {

    @Id
    @GeneratedValue
    private int id;

    @NonNull

    private String name;

    @ManyToOne
    private Rapper rapper;


}
