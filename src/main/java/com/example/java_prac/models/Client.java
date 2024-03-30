package com.example.java_prac.models;


import lombok.*;
import jakarta.persistence.*;

@Entity
@Table(name = "client")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Client implements CommonEntity<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, name = "id")
    private Long id;

    @Column(nullable = false, name="name" )
    private String name;

    @Column(nullable = false, name="telnumber1" )
    private String telnumber1;

    @Column(name="telnumber2" )
    private String telnumber2;

    @Column(name="email" )
    private String email;

    @Column(name="address" )
    private String address;

}
