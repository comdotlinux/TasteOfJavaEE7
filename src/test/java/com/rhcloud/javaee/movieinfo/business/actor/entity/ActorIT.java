/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rhcloud.javaee.movieinfo.business.actor.entity;

import com.linux.rhcloud.javaee.movieinfo.business.actor.entity.Actor;
import javax.persistence.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Guruprasad Kulkarni <guru@linux.com>
 */
public class ActorIT {

    private EntityManager em;
    private EntityTransaction tx;
    
    @Before
    public void setUp() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("it");
        this.em = emf.createEntityManager();
        this.tx = this.em.getTransaction();
    }
    
     @Test
     public void actor_persistence_check() {
         tx.begin();
         em.merge(new Actor("John", "Doe"));
         tx.commit();
     }
    
    
    @After
    public void tearDown() {
        em.clear();
    }

}
