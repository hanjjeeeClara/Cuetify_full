package com.example.demo2.repository;

import java.util.List;

import com.example.demo2.domain.Data;

public interface DataRepository {

    Data save(Data data);
    Data modify(Data data);
    Integer delete(Integer id);
    List<Data> findAll();
    
}
