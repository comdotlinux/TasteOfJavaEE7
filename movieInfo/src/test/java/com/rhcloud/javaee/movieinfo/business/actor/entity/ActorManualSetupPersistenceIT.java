/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rhcloud.javaee.movieinfo.business.actor.entity;

import com.linux.rhcloud.javaee.movieinfo.business.actor.entity.Actor;
import javax.persistence.*;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import org.junit.After;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertTrue;

/**
 *
 * @author Guruprasad Kulkarni <guru@linux.com>
 */
public class ActorManualSetupPersistenceIT {

    private EntityManager em;
    private EntityTransaction tx;
    
    private static final String FN = "John";
    private static final String LN = "Doe";
    
    @Before
    public void setUp() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("it");
        this.em = emf.createEntityManager();
        this.tx = this.em.getTransaction();
    }
    
     @Test
     public void actor_persistence_check() {
         tx.begin();
        Actor actual = em.merge(new Actor(FN, LN));
         tx.commit();
         
        assertThat(actual.getFirstname(), is(FN));
        assertThat(actual.getLastname(), is(LN));
        assertTrue(actual.getVersion() >= 1l);
        assertTrue(actual.getId()>= 1l);
     }
    
    
    @After
    public void tearDown() {
        em.clear();
    }

}
