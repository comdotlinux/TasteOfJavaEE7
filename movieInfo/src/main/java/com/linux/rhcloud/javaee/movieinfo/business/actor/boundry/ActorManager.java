/*
 * Copyright (C) 2016 guru
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.linux.rhcloud.javaee.movieinfo.business.actor.boundry;

import com.linux.rhcloud.javaee.movieinfo.business.actor.entity.Actor;
import com.linux.rhcloud.javaee.movieinfo.business.monitoring.control.InvocationsMonitor;
import com.linux.rhcloud.javaee.movieinfo.crosscutting.logger.boundry.BoundryLogger;
import java.util.List;
import javax.ejb.Stateless;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.interceptor.Interceptors;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.slf4j.Logger;
import static org.slf4j.LoggerFactory.getLogger;

/**
 *
 * @author Guruprasad Kulkarni <guru@linux.com>
 */
@Stateless
@Interceptors(value = {BoundryLogger.class, InvocationsMonitor.class})
public class ActorManager {

    private static final Logger LOG = getLogger(ActorManager.class);
    
    @Inject
    private Event<Actor> actorSaveEvent;
    
    @PersistenceContext
    private EntityManager em;
    
    public List<Actor> all() {
        LOG.info("Running named Query {} on {}", Actor.FIND_ALL_ACTORS, Actor.class.getSimpleName());
        return this.em.createNamedQuery(Actor.FIND_ALL_ACTORS, Actor.class).getResultList();
    }

    /**
     * Create or update Actor
     * @param actor the actor to create or merge
     * @return newly cleated actor.
     */
    public Actor save(Actor actor) {
        LOG.info("Persisting {}", actor);
        this.actorSaveEvent.fire(actor);
        return this.em.merge(actor);
    }

    /**
     * Find by Id
     *
     * @param id id to search
     * @return Actor
     */
    public Actor findById(long id) {
        return this.em.find(Actor.class, id);
    }
    
    public boolean delete(long id){
        try{
            Actor actor = findById(id);
            if(null != actor){
                this.em.remove(actor);
                LOG.warn("Removed actor {}", actor);
                return true;
            } else {
                return false;
            }
        } catch(Exception e){
            LOG.error("Exception occured", e);
            return false;
        }
    }
    
}
