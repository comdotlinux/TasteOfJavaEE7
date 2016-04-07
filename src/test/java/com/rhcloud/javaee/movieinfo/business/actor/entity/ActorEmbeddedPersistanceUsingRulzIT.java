/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rhcloud.javaee.movieinfo.business.actor.entity;

import com.airhacks.rulz.em.EntityManagerProvider;
import com.linux.rhcloud.javaee.movieinfo.business.actor.entity.Actor;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import org.junit.Test;
import org.junit.Rule;

/**
 *
 * @author Guruprasad Kulkarni <guru@linux.com>
 */
public class ActorEmbeddedPersistanceUsingRulzIT {

    @Rule
    public EntityManagerProvider provider = EntityManagerProvider.persistenceUnit("it");

    private static final String FN = "Jane";
    private static final String LN = "Doe";

    @Test
    public void mergeActorUsingRulz() {
        provider.tx().begin();
        Actor actual = provider.em().merge(new Actor(FN, LN));
        provider.tx().commit();

        assertThat(actual.getFirstname(), is(FN));
        assertThat(actual.getLastname(), is(LN));
        assertThat(actual.getVersion(), is(1l));
        assertThat(actual.getId(), is(1l));
    }
}
