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
<<<<<<< HEAD
import java.util.ResourceBundle;
import javax.ws.rs.client.Entity;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.notNullValue;
>>>>>>> origin/master
import org.junit.Test;
import static org.junit.Assert.*;
import static org.junit.Assume.assumeThat;
import org.junit.Rule;

/**
 *
 * @author Guruprasad Kulkarni <guru@linux.com>
 */
public class ActorResourceIT {

    private static final String SERVER_URL;
    
    static {
        ResourceBundle bundle = ResourceBundle.getBundle("application-test");
        final String serverurl = "server.url";
        if (bundle.containsKey(serverurl)){
            SERVER_URL = bundle.getString(serverurl);
        } else {
            SERVER_URL = null;
        }
    }
    
    private static final String URI = SERVER_URL + "/" + JAXRS_BASE + "/" + ACTORS_PATH;
    
    @Rule
    public JAXRSClientProvider provider = JAXRSClientProvider.buildWithURI(URI);
    
    private  JsonObject createActor(String firstname, String lastname){
         JsonObjectBuilder actorBuilder = Json.createObjectBuilder()
                 .add("firstname", firstname)
                 .add("lastname", lastname);
         return actorBuilder.build();
         
    }
    
     @Test
     public void CrudForActorIntegrationTest() {
        final String fn = "Matt";
        final String ln = "Daemon";
         
        Response postResponse = provider.target().request().post(Entity.json(createActor(fn, ln)));
        String location = postResponse.getHeaderString("Location");
        assertThat(location, is(notNullValue()));
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
     }
}
