/*
 * 
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
 * 
 */
package com.linux.rhcloud.javaee.movieinfo.business.monitoring.boundry;

import com.linux.rhcloud.javaee.movieinfo.business.monitoring.entity.CallEvent;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import static javax.ws.rs.core.MediaType.*;

/**
 *
 * @author Guruprasad Kulkarni <guru@linux.com>
 */
@Stateless
@Path("boundry-invocations")
public class InvocationsResource {
    
    @Inject
    MonitorSink sink;
    
    @Path("/actor")
    @GET
    @Produces(value = {APPLICATION_JSON, APPLICATION_XML})
    public List<CallEvent> actorCallList() {
        return sink.getEvents();
    }
    
}
