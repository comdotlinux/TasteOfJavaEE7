/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.linux.rhcloud.javaee.movieinfo.business.actor.boundry;

import static com.linux.rhcloud.javaee.movieinfo.business.actor.boundry.JAXRSConfiguration.JAXRS_BASE;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

/**
 * This is the initializer for Java EE rest web services endpoint.
 * This also denotes the base for all rest web services exposed.
 * @author Guruprasad Kulkarni <guru@linux.com>
 */
@ApplicationPath(JAXRS_BASE)
public class JAXRSConfiguration extends Application{

    /** The root / base for all rest Web services :: has value : {@value #JAXRS_BASE} */
    public static final String JAXRS_BASE = "resources";
}
