package com.project.mnm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;

@Configuration
public class SpringConfig {

    @Autowired
    public SpringConfig(EntityManager entityManager){
    }
}
