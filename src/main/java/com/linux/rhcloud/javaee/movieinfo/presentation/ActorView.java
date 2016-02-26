package com.linux.rhcloud.javaee.movieinfo.presentation;

import com.linux.rhcloud.javaee.movieinfo.business.boundry.ActorManager;
import com.linux.rhcloud.javaee.movieinfo.business.entity.Actor;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import org.slf4j.Logger;
import static org.slf4j.LoggerFactory.getLogger;

/**
 * Backing bean for ActorView.xhtml
 * @author Guruprasad Kulkarni <guru@linux.com>
 */
@Model
public class ActorView {
     private static final Logger LOG = getLogger(ActorView.class);
    
     private Actor actor;
    
     @Inject
     private ActorManager actorManager;
     
     @PostConstruct
     public void init(){
         this.actor = new Actor();
     }

    public Actor getActor() {
        return actor;
    }

    public void setActor(Actor actor) {
        this.actor = actor;
    }
    
    public Object addActor(){
        LOG.info("Actor is {}",this.actor);
        return null;
    }
    
    public List<Actor> allActors(){
        return this.actorManager.all();
    }
     
}
