package com.m2i.demomedical.controller;

import com.m2i.demomedical.entities.PatientEntity;
import com.m2i.demomedical.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/patient")
public class PatientController {

    @Autowired
    final private PatientService ps;

    public PatientController(PatientService ps) {
        this.ps = ps;
    }

    @GetMapping("")
    public String list( Model model ){
        model.addAttribute( "liste_patient" , ps.getList() );
        return "patient/list";
    }


    @GetMapping("/add")
    public String add( Model model ){
        //model.addAttribute( "lp" , ps.getList() );
        return "patient/add_edit";
    }

    @GetMapping("/edit/{id}")
    public String edit( Model model , @PathVariable int id ){
        model.addAttribute( "lp" , ps.find(id) );
        return "patient/add_edit";
    }

}
