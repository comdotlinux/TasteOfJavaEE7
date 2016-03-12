/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rhcloud.javaee.movieinfo.business.actor.boundry;

import com.airhacks.rulz.jaxrsclient.JAXRSClientProvider;
import static com.linux.rhcloud.javaee.movieinfo.business.actor.boundry.ActorResource.ACTORS_PATH;
import static com.linux.rhcloud.javaee.movieinfo.business.actor.boundry.JAXRSConfiguration.JAXRS_BASE;
import javax.json.JsonObject;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Rule;

/**
 *
 * @author Guruprasad Kulkarni <guru@linux.com>
 */
public class ActorResourceIT {
    private static final String URI = "http://localhost:8080/javaee7app/" + JAXRS_BASE + "/" + ACTORS_PATH;
    
    @Rule
    public JAXRSClientProvider provider = JAXRSClientProvider.buildWithURI(URI);
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

     @Test
     public void getAllActors() {
        Response getResponse = provider.target().request(MediaType.APPLICATION_JSON).get();

        
        assertThat(getResponse.getStatus(), is(equalTo(200)));
        assertThat(getResponse.hasEntity(), is(true));
        
         System.out.println("com.rhcloud.javaee.movieinfo.business.actor.boundry.ActorResourceIT.getAllActors() + " + getResponse.getEntity());
     }
}
