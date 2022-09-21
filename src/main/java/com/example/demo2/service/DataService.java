package com.example.demo2.service;
import java.util.List;

import com.example.demo2.domain.Data;
import com.example.demo2.repository.DataRepository;
import com.example.demo2.repository.JdbcTemplateDataRepository;


public class DataService {

    private final DataRepository dataRepository;

    //외부에서 넣어주도록. 매번 객체 생성하지 않고
    public DataService(DataRepository dataRepository){
        this.dataRepository = dataRepository;
    }


    //서비스 기능들 (데이터 한 행 저장, 전체 데이터 불러오기)
    public Data save(Data data){

        return dataRepository.save(data);

    }
    public Integer delete(Integer id){
        return dataRepository.delete(id);
    }

    public Data modify(Data data){

        return dataRepository.modify(data);

    }

    public List<Data> findAll(){

        
        return dataRepository.findAll();

     }
 
}
