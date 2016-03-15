package com.linux.rhcloud.javaee.movieinfo.presentation;

import com.linux.rhcloud.javaee.movieinfo.business.actor.boundry.ActorManager;
import com.linux.rhcloud.javaee.movieinfo.business.actor.entity.Actor;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import org.slf4j.Logger;
import static org.slf4j.LoggerFactory.getLogger;
import static org.slf4j.LoggerFactory.getLogger;

/**
 * Backing bean for ActorView.xhtml
 *
 * @author Guruprasad Kulkarni <guru@linux.com>
 */
@Model
public class ActorView {

    private static final Logger LOG = getLogger(ActorView.class);

    private Actor actor;

    @Inject
    private ActorManager actorManager;
    
    

    @PostConstruct
    public void init() {
        this.actor = new Actor();
    }

    public Actor getActor() {
        return actor;
    }

    public void setActor(Actor actor) {
        this.actor = actor;
    }

    public Object addActor() {
        LOG.info("Adding Actor {}", this.actor);
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Cross Field Validation Failed", "Cross Field Validation Failed");
        FacesContext.getCurrentInstance().addMessage(null, message);
        this.actorManager.save(this.actor);
        return null;
    }

    public List<Actor> getActors() {
        final List<Actor> allActors = this.actorManager.all();
        LOG.info("{} Actors retrieved.", allActors.size());
        return allActors;
    }

}
