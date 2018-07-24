package com.jverson.quartz.job;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class job {
    @Id
    private String nom ;
    private String prenom ;
}
