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
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import org.slf4j.Logger;
import static org.slf4j.LoggerFactory.getLogger;

/**
 * Map all Exceptions wrapped by {@linkplain EJBException} and send appropriate exception response.
 * @author guru
 */
@Provider
public class EJBExceptionMapper implements ExceptionMapper<EJBException>{

    private static final String CAUSE = "Cause";
    
    private static final Logger LOG = getLogger(EJBExceptionMapper.class);
    
    @Override
    public Response toResponse(EJBException ejbEx) {
        LOG.warn("EJBException mapper called.");
        Throwable cause = ejbEx.getCause();
        
        
        if(cause instanceof OptimisticLockException){
            final OptimisticLockException actual = (OptimisticLockException) cause;
            LOG.warn("OptimisticLockException thrown", cause);
            return Response.status(Response.Status.CONFLICT)
                    .header(CAUSE, "There was a conflict. Details are : " + actual.getEntity())
                    .header("additional-info", actual.getMessage())
                    .build();
        }
        
        
        // Build default response.
        return Response
                .serverError()
                .header(CAUSE, ejbEx.toString())
                .build();
    }
    
}
