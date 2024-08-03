package com.example.HospitalManagement.System;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/show")
public class MyControllers {

    @GetMapping("/name")
    public String showName(){
        return "Dinesh";
    }
}
