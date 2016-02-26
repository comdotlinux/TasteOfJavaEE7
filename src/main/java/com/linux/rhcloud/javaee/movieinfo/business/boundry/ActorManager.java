package com.linux.rhcloud.javaee.movieinfo.business.boundry;

import com.linux.rhcloud.javaee.movieinfo.business.entity.Actor;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Guruprasad Kulkarni <guru@linux.com>
 */
@Stateless
public class ActorManager {

    @PersistenceContext
    private EntityManager em;
    
    public List<Actor> all() {
        return em.createNamedQuery(Actor.FIND_ALL_ACTORS, Actor.class).getResultList();
    }
    
}
