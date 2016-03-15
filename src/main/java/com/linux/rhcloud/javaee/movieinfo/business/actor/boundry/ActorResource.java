/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.linux.rhcloud.javaee.movieinfo.business.actor.boundry;

import com.linux.rhcloud.javaee.movieinfo.business.actor.entity.Actor;
import javax.ws.rs.GET;

/**
 *
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
    public Actor getById(){
        return this.manager.findById(id);
    }
}
