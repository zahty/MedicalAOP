package com.m2i.demomedical.service;

import com.m2i.demomedical.entities.PatientEntity;
import com.m2i.demomedical.entities.VilleEntity;
import com.m2i.demomedical.repository.PatientRepository;
import org.springframework.stereotype.Service;

@Service
public class PatientService {

    private PatientRepository pr;

    public PatientService(PatientRepository pr) {
        this.pr = pr;
    }

    public Iterable<PatientEntity> getList(){
        return pr.findAll();
    }

    public PatientEntity addPatient(String nom, String prenom, String email, String telephone , int ville ){
        try{
            PatientEntity p = new PatientEntity();
            p.setNom(nom);
            p.setPrenom(prenom);
            p.setEmail(email);
            p.setTelephone(telephone);

            VilleEntity villeP = new VilleEntity();
            villeP.setId( ville );
            p.setVille( villeP );

            pr.save( p );
            return  p;

        }catch( Exception e ){

        }

        return null;
    }

    public PatientEntity find(int id) {
        return pr.findById( id ).get();
    }
}
