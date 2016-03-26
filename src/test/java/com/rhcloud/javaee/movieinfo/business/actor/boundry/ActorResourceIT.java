/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rhcloud.javaee.movieinfo.business.actor.boundry;

import static com.airhacks.rulz.jaxrsclient.HttpMatchers.successful;
import com.airhacks.rulz.jaxrsclient.JAXRSClientProvider;
import static com.linux.rhcloud.javaee.movieinfo.business.actor.boundry.ActorsResource.ACTORS_PATH;
import static com.linux.rhcloud.javaee.movieinfo.business.actor.boundry.JAXRSConfiguration.JAXRS_BASE;
import java.util.ResourceBundle;
import javax.ws.rs.client.Entity;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import javax.ws.rs.core.Response;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.notNullValue;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.junit.Assume.assumeThat;
import org.junit.Ignore;
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
        if (bundle.containsKey(serverurl)) {
            SERVER_URL = bundle.getString(serverurl);
        } else {
            SERVER_URL = null;
        }
    }
    private static final String FORWARD_SLASH = "/";

    private static final String URI = SERVER_URL + FORWARD_SLASH + JAXRS_BASE;

    @Rule
    public JAXRSClientProvider provider = JAXRSClientProvider.buildWithURI(URI);

    private JsonObject createActor(String firstname, String lastname) {
        JsonObjectBuilder actorBuilder = Json.createObjectBuilder()
                .add(FIRSTNAME, firstname)
                .add(LASTNAME, lastname);
        return actorBuilder.build();

    }
    private static final String LASTNAME = "lastname";
    private static final String FIRSTNAME = "firstname";

    @Test
    public void actor_integration_CRUD() {
        // Get Actor
        Response getResponse = null;        
        try {
            getResponse = provider.target().path(FORWARD_SLASH + ACTORS_PATH + FORWARD_SLASH + 1).request(APPLICATION_JSON).get();
        } finally {
            System.out.println("Server responded ? " + (getResponse != null));
            assumeThat(getResponse, is(notNullValue()));
        }

        final String fn = "John";
        final String ln = "Doe";

        // Post Actor
        Response postResponse = provider.target().path(FORWARD_SLASH + ACTORS_PATH).request(APPLICATION_JSON).post(Entity.json(createActor(fn, ln)));
        assertThat(postResponse, is(successful()));

        String location = postResponse.getHeaderString("Location");
        assertThat(location, is(notNullValue()));
        System.out.println("ActorResourceIT.actor_integration_CRUD() Post Actor Response Location : " + location);

        // Get Actor
        Response actorResponse = provider.target(location).request(APPLICATION_JSON).get();
        assertThat(actorResponse, is(successful()));

        JsonObject actor = actorResponse.readEntity(JsonObject.class);
        assertThat(actor.getString(FIRSTNAME), is(equalTo(fn)));
        assertThat(actor.getString(LASTNAME), is(equalTo(ln)));
        System.out.println("ActorResourceIT.actor_integration_CRUD() Get Actor : " + actor);
        
        // Update Actor
        final String newFn = "Jane";
        JsonObject actorUpdate = Json.createObjectBuilder()
                .add(FIRSTNAME, newFn)
                .add(LASTNAME, ln)
                .add("version", actor.getInt("version"))
                .build();
        Response putResponse = provider.target(location).request(APPLICATION_JSON).put(Entity.json(actorUpdate));
        assertThat(putResponse, is(successful()));
        
        JsonObject updatedActor = putResponse.readEntity(JsonObject.class);
        assertThat(updatedActor.getString(FIRSTNAME), is(equalTo(newFn)));
        assertThat(updatedActor.getString(LASTNAME), is(equalTo(ln)));
        System.out.println("ActorResourceIT.actor_integration_CRUD() Put Actor Response : " + updatedActor);
        
        // Delete Actor
        Response deleteActor = provider.target(location).request(APPLICATION_JSON).delete();
        assertThat(deleteActor, is(successful()));
        System.out.println("ActorResourceIT.actor_integration_CRUD() Delete Actor Response : " + deleteActor);

    }
    
    @Ignore
    @Test
    public void actor_integration_OptimisticLockingCheck() {
        // Get Actor
        Response getResponse = null;        
        try {
            getResponse = provider.target().path(FORWARD_SLASH + ACTORS_PATH + FORWARD_SLASH + 1).request(APPLICATION_JSON).get();
        } finally {
            System.out.println("Server responded ? " + (getResponse != null));
            assumeThat(getResponse, is(notNullValue()));
        }

        final String fn = "John";
        final String ln = "Doe";

        // Post Actor
        Response postResponse = provider.target().path(FORWARD_SLASH + ACTORS_PATH).request(APPLICATION_JSON).post(Entity.json(createActor(fn, ln)));
        assertThat(postResponse, is(successful()));

        String location = postResponse.getHeaderString("Location");
        assertThat(location, is(notNullValue()));
        System.out.println("ActorResourceIT.actor_integration_CRUD() Post Actor Response Location : " + location);

        // Get Actor
        Response actorResponse = provider.target(location).request(APPLICATION_JSON).get();
        assertThat(actorResponse, is(successful()));

        JsonObject actor = actorResponse.readEntity(JsonObject.class);
        assertThat(actor.getString(FIRSTNAME), is(equalTo(fn)));
        assertThat(actor.getString(LASTNAME), is(equalTo(ln)));
        System.out.println("ActorResourceIT.actor_integration_CRUD() Get Actor : " + actor);

        // Update Actor Once
        final String newFn = "Jane";
        JsonObject actorUpdate = Json.createObjectBuilder().add(FIRSTNAME, newFn).build();
        Response putResponse = provider.target(location).request(APPLICATION_JSON).put(Entity.json(actorUpdate));
        assertThat(putResponse, is(successful()));
        
        JsonObject updatedActor = actorResponse.readEntity(JsonObject.class);
        assertThat(updatedActor.getString(FIRSTNAME), is(equalTo(newFn)));
        assertThat(updatedActor.getString(LASTNAME), is(equalTo(ln)));
        System.out.println("ActorResourceIT.actor_integration_CRUD() Put Actor Response : " + updatedActor);
        
        
        
        
        // Delete Actor
        Response deleteActor = provider.target(location).request(APPLICATION_JSON).delete();
        assertThat(deleteActor, is(successful()));
        System.out.println("ActorResourceIT.actor_integration_CRUD() Delete Actor Response : " + deleteActor);

    }

    @Test
    public void get_all_actors() {

        Response getResponse = null;

        try {
            getResponse = provider.target().path("/" + ACTORS_PATH).request(APPLICATION_JSON).get();
        } finally {
            System.out.println("Server responded ? " + (getResponse != null));
            assumeThat(getResponse, is(notNullValue()));
        }

        assertThat(getResponse, is(successful()));
        assertThat(getResponse.hasEntity(), is(true));

        JsonArray payload = getResponse.readEntity(JsonArray.class);
        assertThat(payload, is(notNullValue()));
        final int size = payload.size();
        assertThat(size, is(not(0)));

        JsonObject value = payload.getJsonObject(size - 1);
        assertThat(value, is(notNullValue()));
        assertThat(value.getInt("id") >= size, is(true));
    }
}
