package com.linux.rhcloud.javaee.movieinfo.presentation;

import com.linux.rhcloud.javaee.movieinfo.business.actor.boundry.ActorManager;
import com.linux.rhcloud.javaee.movieinfo.business.actor.entity.Actor;
import java.util.List;
import java.util.Set;
import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.validation.ConstraintViolation;
import javax.validation.Path;
import javax.validation.Validator;
import org.slf4j.Logger;
import static org.slf4j.LoggerFactory.getLogger;
import static org.slf4j.LoggerFactory.getLogger;

/**
 * Backing bean for Actors.xhtml
 *
 * @author Guruprasad Kulkarni <guru@linux.com>
 */
@Model
public class Actors {

    private static final Logger LOG = getLogger(Actors.class);

    private Actor actor;

    @Inject
    ActorManager actorManager;
    
    @Inject
    Validator validator;
    
    

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
        Set<ConstraintViolation<Actor>> actorViolations = validator.validate(this.actor, Actor.class);
        LOG.info("Actor violations are {} ", actorViolations);
        if(!actorViolations.isEmpty()){
            for (ConstraintViolation<Actor> actorViolation : actorViolations) {
                Path propertyPath = actorViolation.getPropertyPath();
                String message = actorViolation.getMessage();
                LOG.warn("Field {} failed validation woth message {}", propertyPath, message);
                FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Cross Field Validation Failed for " + propertyPath.toString(), message);
                FacesContext.getCurrentInstance().addMessage(null, facesMsg);
            }
        } else {
            this.actorManager.save(this.actor);
        }
        return null;
    }

    public List<Actor> getActors() {
        final List<Actor> allActors = this.actorManager.all();
        LOG.info("{} Actors retrieved.", allActors.size());
        return allActors;
    }

}
