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
package com.linux.rhcloud.javaee.movieinfo.business;

import javax.ejb.EJBException;
import javax.persistence.OptimisticLockException;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

/**
 * Map all Exceptions wrapped by {@linkplain EJBException} and send appropriate exception response.
 * @author guru
 */
@Produces
public class EJBExceptionMapper implements ExceptionMapper<EJBException>{

    private static final String CAUSE = "cause";
    
    
    @Override
    public Response toResponse(EJBException ejbEx) {
        Throwable cause = ejbEx.getCause();
        
        // Build default response.
        Response errorResponse = Response
                .serverError()
                .header(CAUSE, ejbEx.toString())
                .build();
        
        if(cause instanceof OptimisticLockException){
            errorResponse = Response.status(Response.Status.CONFLICT)
                    .header(CAUSE, "There was a conflict. Details are : " + cause)
                    .build();
        }
        
        
        return errorResponse;
    }
    
}
