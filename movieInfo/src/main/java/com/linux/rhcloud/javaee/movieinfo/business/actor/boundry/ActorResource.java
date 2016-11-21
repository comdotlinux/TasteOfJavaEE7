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
