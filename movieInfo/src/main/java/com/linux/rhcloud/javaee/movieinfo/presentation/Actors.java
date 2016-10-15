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
        Set<ConstraintViolation<Actor>> actorViolations = validator.validate(this.actor);
        LOG.info("Actor violations are {} ", actorViolations);
        LOG.info("checking if Violations are empty.");
        if(!actorViolations.isEmpty()){
            LOG.info("Violations found!!!!!");
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

    public List<Actor> getAllActors() {
        final List<Actor> allActors = this.actorManager.all();
        LOG.info("{} Actors retrieved.", allActors.size());
        return allActors;
    }

}
