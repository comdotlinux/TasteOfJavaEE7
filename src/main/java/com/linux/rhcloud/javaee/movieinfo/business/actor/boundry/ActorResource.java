package com.linux.rhcloud.javaee.movieinfo.business.actor.boundry;

import com.linux.rhcloud.javaee.movieinfo.business.actor.entity.Actor;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;

/**
 * Sub resource for Actors
 * @author Guruprasad Kulkarni <guru@linux.com>
 */
class ActorResource {

    private final long id;
    private final ActorManager manager;
    
    public ActorResource(long id, ActorManager manager){
        this.id = id;
        this.manager = manager;
    }
    
    @GET
    public Actor find(){
        return this.manager.findById(id);
    }
    
    @PUT
    public Actor update(){
        return this.manager.findById(id);
    }
    
    @DELETE
    public Actor delete(){
        return this.manager.findById(id);
    }
}
