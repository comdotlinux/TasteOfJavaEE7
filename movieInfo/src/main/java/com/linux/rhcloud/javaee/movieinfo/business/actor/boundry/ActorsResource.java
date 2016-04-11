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

import static com.linux.rhcloud.javaee.movieinfo.business.actor.boundry.ActorsResource.ACTORS_PATH;
import com.linux.rhcloud.javaee.movieinfo.business.actor.entity.Actor;
import java.net.URI;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import org.slf4j.Logger;
import static org.slf4j.LoggerFactory.getLogger;

/**
 * The Base Rest resource for accessing / updating Actors.
 * 
 * @author Guruprasad Kulkarni <guru@linux.com>
 */
@Path(ACTORS_PATH)
@Stateless
@Produces({APPLICATION_JSON})
public class ActorsResource {
    
    /** The Path for {@link ActorsResource}. The value is {@value #ACTORS_PATH}*/
    public static final String ACTORS_PATH = "actors";
    
    private static final Logger LOG = getLogger(ActorsResource.class);

    @Inject
    ActorManager manager;
    
    /**
     * The Get resource
     * @return {@linkplain List} of {@linkplain Actor}
     */
    @GET
    public List<Actor> getAllActors(){
        return manager.all();
    }
    
    @GET
    @Path("{id}")
    public Actor find(@PathParam("id") long id){
        return this.manager.findById(id);
    }
    
    @PUT
    @Path("{id}")
    public Actor update(@PathParam("id") long id, @NotNull Actor actor){
        if(actor.getId() != id){
            actor.setId(id);
        }
        return this.manager.save(actor);
    }
    
    @DELETE
    @Path("{id}")
    public Response update(@PathParam("id") long id){
            
        Response.Status status = this.manager.delete(id)? Response.Status.RESET_CONTENT : Response.Status.BAD_REQUEST;
        return Response.status(status).build();
    }
    
     /**
     * Add an actor
     * @param actor Actor to add
     * @param uriInfo instance of UriInfo to get absolute URI.
     * @return
     */
    @POST
    public Response addActor(@NotNull @Valid Actor actor, @Context UriInfo uriInfo){
        LOG.info("Creating Actor : {}", actor);
        Actor newActor = this.manager.save(actor);
        LOG.info("Created Actor : {}", newActor);
        URI location = uriInfo.getAbsolutePathBuilder().path("/" + newActor.getId()).build();
        LOG.info("Created URI for location header is {}", location);
        return Response.created(location).build();
    }

}
