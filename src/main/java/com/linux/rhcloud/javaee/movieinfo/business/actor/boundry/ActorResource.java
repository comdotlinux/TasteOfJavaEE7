/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.linux.rhcloud.javaee.movieinfo.business.actor.boundry;

import static com.linux.rhcloud.javaee.movieinfo.business.actor.boundry.ActorResource.ACTORS_PATH;
import com.linux.rhcloud.javaee.movieinfo.business.actor.entity.Actor;
import java.util.List;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import javax.ws.rs.core.Response;

/**
 *
 * @author Guruprasad Kulkarni <guru@linux.com>
 */
@Path(ACTORS_PATH)
public class ActorResource {
    
    /** The Path for {@linkplain ActorResource}. The value is {@value #ACTORS_PATH}*/
    public static final String ACTORS_PATH = "actors";

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
    
    @POST
    public Response createActor(){
        return null;
    }
}
