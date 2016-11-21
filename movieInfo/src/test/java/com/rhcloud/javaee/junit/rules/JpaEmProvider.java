/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rhcloud.javaee.junit.rules;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;

/**
 *
 * @author Guruprasad Kulkarni <guru@linux.com>
 */
public class JpaEmProvider implements TestRule{

    private final EntityManager em;
    private final EntityTransaction tx;
    
    private JpaEmProvider(String puName){
        this.em = Persistence.createEntityManagerFactory(puName).createEntityManager();
        this.tx = this.em.getTransaction();
    }
    
    public static JpaEmProvider persistenceUnit(String pu){
        return new JpaEmProvider(pu);                
    }

    public EntityManager em() {
        return em;
    }

    public EntityTransaction tx() {
        return tx;
    }
    
    public void start(){
        tx.begin();
    }
    
    public void stop(){
        tx.commit();
    }
    
    public void abort(){
        tx.rollback();
    }
    

    @Override
    public Statement apply(Statement baseStmnt, Description d) {
        return new Statement(){
            @Override
            public void evaluate() throws Throwable {
                baseStmnt.evaluate();
                em.clear();
            }
            
        };
    }
}
