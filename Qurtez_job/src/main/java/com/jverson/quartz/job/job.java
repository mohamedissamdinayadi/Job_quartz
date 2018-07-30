package com.jverson.quartz.job;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
public class job implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  String nom ;
    private  String prenom ;
}
