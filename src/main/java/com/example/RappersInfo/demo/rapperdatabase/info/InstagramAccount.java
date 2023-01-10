package com.example.RappersInfo.demo.rapperdatabase.info;

import javax.persistence.*;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name="ig_account")

public class InstagramAccount {

    @Id
    @GeneratedValue
    private int id;
    @NonNull
    private String name;

    private int followers;

    @OneToOne
    @JoinColumn(name = "rapper_id")
    @OnDelete(action = OnDeleteAction.CASCADE)

    private Rapper rapper;
}
