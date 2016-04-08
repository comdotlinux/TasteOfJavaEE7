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
import static com.linux.rhcloud.javaee.movieinfo.business.actor.entity.Actor.FIND_ALL_ACTORS;
import java.util.Arrays;
import java.util.List;
import javax.persistence.TypedQuery;
import static org.junit.Assert.assertTrue;
import org.junit.Before;

/**
 *
 * @author Guruprasad Kulkarni <guru@linux.com>
 */
public class ActorEmbeddedPersistanceUsingRulzIT {

    @Rule
    public EntityManagerProvider provider = EntityManagerProvider.persistenceUnit("it");

    private static final String FN = "Jane";
    private static final String LN = "Doe";
    
    @Before
    public void setUp(){
        provider.tx().begin();
        provider.em().createNativeQuery("TRUNCATE TABLE actor;");
        provider.tx().commit();
    }
    

    @Test
    public void mergeActorUsingRulz() {
        provider.tx().begin();
        Actor actual = provider.em().merge(new Actor(FN, LN));
        provider.tx().commit();

        System.out.println("ActorEmbeddedPersistanceUsingRulzIT.mergeActorUsingRulz() actual :: " + actual);
        assertThat(actual.getFirstname(), is(FN));
        assertThat(actual.getLastname(), is(LN));
        assertTrue(actual.getVersion() >= 1l);
        assertTrue(actual.getId() >= 1l);
    }

    @Test
    public void getAllActors() {

        List<Actor> actors = Arrays.asList(new Actor("One", "One"), new Actor("Two", "Two"), new Actor("Three", "Three"));

        this.provider.tx().begin();
        actors.stream().forEach((a) -> {
            this.provider.em().merge(a);
        });
        this.provider.tx().commit();

        this.provider.tx().begin();
        TypedQuery<Actor> createNamedQuery = this.provider.em().createNamedQuery(FIND_ALL_ACTORS, Actor.class);
        List<Actor> resultList = createNamedQuery.getResultList();
        this.provider.tx().commit();

        assertThat(resultList.size(), is(actors.size()));

    }

}
