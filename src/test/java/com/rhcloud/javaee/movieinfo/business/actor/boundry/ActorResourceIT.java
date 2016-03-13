/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rhcloud.javaee.movieinfo.business.actor.boundry;

import com.airhacks.rulz.jaxrsclient.HttpMatchers;
import com.airhacks.rulz.jaxrsclient.JAXRSClientProvider;
import static com.linux.rhcloud.javaee.movieinfo.business.actor.boundry.ActorResource.ACTORS_PATH;
import static com.linux.rhcloud.javaee.movieinfo.business.actor.boundry.JAXRSConfiguration.JAXRS_BASE;
import com.linux.rhcloud.javaee.movieinfo.business.actor.entity.Actor;
import java.lang.reflect.InvocationTargetException;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.json.JsonValue;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.hamcrest.CoreMatchers;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.CoreMatchers.nullValue;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Assume;
import static org.junit.Assume.assumeThat;
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
         
        Response getResponse = null;
        
        try{
                
                getResponse = provider.target().request(MediaType.APPLICATION_JSON).get();
        } finally {
            assumeThat(getResponse,is(notNullValue()));
        }

        
        assertThat(getResponse, is(HttpMatchers.successful()));
        assertThat(getResponse.hasEntity(), is(true));
        
        JsonArray payload = getResponse.readEntity(JsonArray.class);
        assertThat(payload, is(notNullValue()));
        final int size = payload.size();
        assertThat(size, is(not(0)));
        
        JsonObject value = payload.getJsonObject(size - 1);
        assertThat(value, is(notNullValue()));
        assertThat(value.getInt("id"), is(size));
        
        
        JsonObjectBuilder actorBuilder = Json.createObjectBuilder();
        
        
         
     }
}
