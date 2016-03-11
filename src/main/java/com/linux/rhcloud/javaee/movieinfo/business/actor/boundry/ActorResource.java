/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.linux.rhcloud.javaee.movieinfo.business.actor.boundry;

import com.linux.rhcloud.javaee.movieinfo.business.actor.entity.Actor;
import static com.linux.rhcloud.javaee.movieinfo.business.actor.entity.Actor.ID;
import java.math.BigDecimal;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

/**
 *
 * @author Guruprasad Kulkarni <guru@linux.com>
 */
@Path("actors")
public class ActorResource {

    @Inject
    ActorManager manager;
    
    /**
     * The Get resource
     * @return {@linkplain List} of {@linkplain Actor}
     */
    @GET
    @Produces(APPLICATION_JSON)
    public List<Actor> getAllActors(){
        return manager.all();
    }
}
