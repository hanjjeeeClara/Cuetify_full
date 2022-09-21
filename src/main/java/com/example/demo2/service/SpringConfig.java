package com.example.demo2.service;

import javax.sql.DataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.beans.factory.annotation.Autowired;


import com.example.demo2.repository.DataRepository;
import com.example.demo2.repository.JdbcTemplateDataRepository;

@Configuration
public class SpringConfig {

    private  DataSource dataSource;
    //private  DataRepository dataRepository;
    

    //jdbc 사용하는 경우
    @Autowired 
    public SpringConfig(DataSource dataSource){this.dataSource = dataSource;}

    @Bean
    public DataService dataService(){return new DataService(dataRepository());}



    @Bean
    public DataRepository dataRepository() {
        
        return new JdbcTemplateDataRepository(dataSource); //DB 사용하는 경우
        

    }


}
