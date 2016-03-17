package com.linux.rhcloud.javaee.movieinfo.business.actor.boundry;

import static com.linux.rhcloud.javaee.movieinfo.business.actor.boundry.ActorsResource.ACTORS_PATH;
import com.linux.rhcloud.javaee.movieinfo.business.actor.entity.Actor;
import java.net.URI;
import java.util.List;
import javax.inject.Inject;
import javax.json.JsonObject;
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
public class ActorsResource {
    
    /** *  The Path for {@link ActorsResource}. The value is {@value #ACTORS_PATH}*/
    public static final String ACTORS_PATH = "actors";
    
    private static final Logger LOG = getLogger(ActorsResource.class);

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
    
    @GET
    @Path("{id}")
    @Produces(APPLICATION_JSON)
    public Actor find(@PathParam("id") long id){
        return this.manager.findById(id);
    }
    
    @PUT
    @Path("{id}")
    @Produces(APPLICATION_JSON)
    public Actor update(@PathParam("id") long id, @NotNull @Valid Actor actor){
        if(actor.getId() != id){
            actor.setId(id);
        }
        return this.manager.save(actor);
    }
    
    @DELETE
    @Path("{id}")
    @Produces(APPLICATION_JSON)
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
    @Produces(APPLICATION_JSON)
    public Response addActor(@NotNull @Valid Actor actor, @Context UriInfo uriInfo){
        LOG.info("Creating Actor : {}", actor);
        Actor newActor = this.manager.save(actor);
        LOG.info("Created Actor : {}", newActor);
        URI location = uriInfo.getAbsolutePathBuilder().path("/" + newActor.getId()).build();
        LOG.info("Created URI for location header is {}", location);
        return Response.created(location).build();
    }

}