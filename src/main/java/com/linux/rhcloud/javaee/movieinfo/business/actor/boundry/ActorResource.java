/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.linux.rhcloud.javaee.movieinfo.business.actor.boundry;

import static com.linux.rhcloud.javaee.movieinfo.business.actor.boundry.ActorResource.ACTORS_PATH;
import com.linux.rhcloud.javaee.movieinfo.business.actor.entity.Actor;
import java.net.URI;
import java.util.List;
import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.GET;
import static javax.ws.rs.HttpMethod.POST;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import org.slf4j.Logger;
import static org.slf4j.LoggerFactory.getLogger;

/**
 *
 * @author Guruprasad Kulkarni <guru@linux.com>
 */
@Path(ACTORS_PATH)
public class ActorResource {
    
    /** The Path for {@linkplain ActorResource}. The value is {@value #ACTORS_PATH}*/
    public static final String ACTORS_PATH = "actors";
    
    private static final Logger LOG = getLogger(ActorResource.class);

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
    
    /**
     * Add an actor
     * @param actor Actor to add
     * @param uriInfo instance of UriInfo to get absolute URI.
     * @return
     */
    @POST
    @Produces(APPLICATION_JSON)
    public Response addActor(@Valid Actor actor, @Context UriInfo uriInfo){
        LOG.info("Creating Actor : {}", actor);
        Actor newActor = this.manager.save(actor);
        LOG.info("Created Actor : {}", newActor);
        URI location = uriInfo.getAbsolutePathBuilder().path("/" + newActor.getId()).build();
        LOG.info("Created URI for location header is {}", location);
        return Response.created(location).build();
    }
}
