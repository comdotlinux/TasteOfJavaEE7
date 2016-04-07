/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rhcloud.javaee.movieinfo.business.actor.entity;

import com.linux.rhcloud.javaee.movieinfo.business.actor.entity.Actor;
import com.rhcloud.javaee.junit.rules.JpaEmProvider;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Rule;

/**
 *
 * @author Guruprasad Kulkarni <guru@linux.com>
 */
public class ActorUseJunitRuleIT {
    
    @Rule
    public JpaEmProvider provider = JpaEmProvider.persistenceUnit("it");
    
     @Test
     public void actor_persistence_check() {
         provider.start();
         provider.em().merge(new Actor("John", "Doe"));
         provider.stop();
     }
}
