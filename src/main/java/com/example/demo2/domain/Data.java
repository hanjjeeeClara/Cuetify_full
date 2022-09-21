package com.example.demo2.domain;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter

@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Data {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) //identity:db가 알아서 생성해줌
    private Long id; //시스템이 알아서 정해주는 용도
    
    @Column(name = "name")
    private String name;

    @Column(name = "kind")
    private String kind;

    @Column(name = "price")
    private String price;

    @Column(name = "date_")
    private String date;

    @Column(name = "seller")
    private String seller;

    @Column(name = "buyer")
    private String buyer;
    
}
