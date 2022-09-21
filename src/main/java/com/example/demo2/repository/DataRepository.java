package com.example.demo2.repository;

import java.util.List;

import com.example.demo2.domain.Data;

public interface DataRepository {

    Data save(Data data);
    List<Data> findAll();
    
}
