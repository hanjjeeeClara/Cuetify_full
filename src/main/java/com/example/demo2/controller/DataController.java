package com.example.demo2.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo2.domain.Data;
import com.example.demo2.service.DataService;


@Controller
public class DataController {

    
    private final DataService dataService;

    @Autowired
    public DataController(DataService dataService) {
        this.dataService = dataService;
    }

    //@GetMapping("api")
    @ResponseBody
    @RequestMapping("api")
    public List<Data> all(){


        //데이터 불러오기
        System.out.println("service.findall전");
        
        //불러온 데이터 사용하기
        List<Data>all_data = new ArrayList<>();
        
        all_data = dataService.findAll();
        //model.addAttribute("model", all_data);
        System.out.println("service.findall후");

        
        return all_data;
    }

    //데이터 추가 
    @PostMapping("api/dataform")
    public String save(@RequestBody DataForm form
        /* 
        @RequestParam("name") String name,
        @RequestParam("kind") String kind,
        @RequestParam("price") String price,
        @RequestParam("date") String date,
        @RequestParam("seller") String seller,
        @RequestParam("buyer") String buyer
        */
    
    ){

        
        //get 방식
        //DataForm form = new DataForm();
        // form.setName(name);
        // form.setKind(kind);
        // form.setPrice(price);
        // form.setDate(date);
        // form.setSeller(seller);
        // form.setBuyer(buyer);


        //확인
        System.out.println("form 데이터 확인:"+form.getName()+","+form.getKind());

        Data data = new Data();
        System.out.println("after create data");

        data.setName(form.getName());
        data.setKind(form.getKind());
        data.setPrice(form.getPrice());
        data.setDate(form.getDate());
        data.setSeller(form.getSeller());
        data.setBuyer(form.getBuyer());

        System.out.println("after set data name");

        //확인
        System.out.println("data = " + data.getName());

        //데이터추가하여 저장
        System.out.println("service.save전");
        dataService.save(data);
        System.out.println("service.save후");

        return "redirect:/";

    }


    //데이터 삭제
    @PostMapping("api/datadelete")
    public String save(@RequestBody IdFrom id){

    
        //확인
        System.out.println("데이터 확인-id : "+ id.getId() );
        dataService.delete(id.getId());
        return "redirect:/";

    }

    //데이터 수정
    @PostMapping("api/datamodify")
    public String modify(@RequestBody DataForm form){

    
        //확인
        System.out.println("form 데이터 확인:"+form.getName()+","+form.getKind());

        Data data = new Data();
        System.out.println("after create data");

        data.setName(form.getName());
        data.setKind(form.getKind());
        data.setPrice(form.getPrice());
        data.setDate(form.getDate());
        data.setSeller(form.getSeller());
        data.setBuyer(form.getBuyer());
        data.setId(form.getId());

        System.out.println("after set data name");

        //확인
        System.out.println("data = " + data.getName());

        //데이터추가하여 저장
        System.out.println("service.modify전");
        dataService.modify(data);
        System.out.println("service.modify후");

        return "redirect:/";


    }

   
}
