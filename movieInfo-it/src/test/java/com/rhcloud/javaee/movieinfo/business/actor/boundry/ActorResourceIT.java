/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rhcloud.javaee.movieinfo.business.actor.boundry;

import static com.airhacks.rulz.jaxrsclient.HttpMatchers.successful;
import com.airhacks.rulz.jaxrsclient.JAXRSClientProvider;
import java.util.ResourceBundle;
import javax.ws.rs.client.Entity;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import static org.apache.commons.lang3.StringUtils.EMPTY;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.notNullValue;
import org.hamcrest.CustomMatcher;
import org.hamcrest.Matcher;
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
    public static final String ACTORS_PATH = "actors";
    public static final String JAXRS_BASE = "resources";

    static {
        ResourceBundle bundle = ResourceBundle.getBundle("com.rhcloud.javaee.application-test");
        final String serverurl = "server.url";
        if (bundle.containsKey(serverurl)) {
            SERVER_URL = bundle.getString(serverurl);
        } else {
            SERVER_URL = EMPTY;
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
    private static final String VERSION = "version";

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

        try {

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
                    .build();
            Response putResponse = provider.target(location).request(APPLICATION_JSON).put(Entity.json(actorUpdate));
            assertThat(putResponse, is(successful()));

            JsonObject updatedActor = putResponse.readEntity(JsonObject.class);
            assertThat(updatedActor.getString(FIRSTNAME), is(equalTo(newFn)));
            assertThat(updatedActor.getString(LASTNAME), is(equalTo(ln)));
            System.out.println("ActorResourceIT.actor_integration_CRUD() Put Actor Response : " + updatedActor);

        } finally {
            // Delete Actor
            Response deleteActor = provider.target(location).request(APPLICATION_JSON).delete();
            assertThat(deleteActor, is(successful()));
            System.out.println("ActorResourceIT.actor_integration_CRUD() Delete Actor Response : " + deleteActor);
        }

    }

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
        System.out.println("ActorResourceIT.actor_integration_OptimisticLockingCheck() Post Actor Response Location : " + location);

        try {
            // Get Actor
            Response actorResponse = provider.target(location).request(APPLICATION_JSON).get();
            assertThat(actorResponse, is(successful()));

            JsonObject actor = actorResponse.readEntity(JsonObject.class);
            assertThat(actor.getString(FIRSTNAME), is(equalTo(fn)));
            assertThat(actor.getString(LASTNAME), is(equalTo(ln)));
            assertThat(actor.keySet(), hasItem(VERSION));

            long version = actor.getJsonNumber(VERSION).longValue();
            System.out.println("ActorResourceIT.actor_integration_OptimisticLockingCheck() Get Actor : " + actor);

            // Update Actor Once
            final String newFn = "Jane";
            JsonObject actorUpdate = Json.createObjectBuilder()
                    .add(FIRSTNAME, newFn)
                    .add(LASTNAME, ln)
                    .add(VERSION, version)
                    .build();
            Response putResponse = provider.target(location).request(APPLICATION_JSON).put(Entity.json(actorUpdate));
            assertThat(putResponse, is(successful()));

            JsonObject updatedActor = putResponse.readEntity(JsonObject.class);
            assertThat(updatedActor.getString(FIRSTNAME), is(equalTo(newFn)));
            assertThat(updatedActor.getString(LASTNAME), is(equalTo(ln)));
            System.out.println("ActorResourceIT.actor_integration_OptimisticLockingCheck() update Actor once Response : " + updatedActor);

            // Update Actor Second time
            final String newFn2 = "Jamie";
            actorUpdate = Json.createObjectBuilder()
                    .add(FIRSTNAME, newFn2)
                    .add(LASTNAME, ln)
                    .add(VERSION, version)
                    .build();
            putResponse = provider.target(location).request(APPLICATION_JSON).put(Entity.json(actorUpdate));
            assertThat(putResponse.getStatus(), is(409));
            System.out.println("ActorResourceIT.actor_integration_OptimisticLockingCheck() update Actor second time Response : " + putResponse);
            final MultivaluedMap<String, Object> putResponseHeaders = putResponse.getHeaders();
            putResponseHeaders.forEach((key, value) -> {
                System.out.println("actor_integration_OptimisticLockingCheck() : key : " + key + ", value : " + value);
            });

            assertThat((String) putResponseHeaders.getFirst("Cause"), containsString("There was a conflict. Details are :"));
            assertThat((String) putResponseHeaders.getFirst("additional-info"), containsString("Row was updated or deleted by another transaction"));

        } finally {
            // Delete Actor
            Response deleteActor = provider.target(location).request(APPLICATION_JSON).delete();
            assertThat(deleteActor, is(successful()));
            System.out.println("ActorResourceIT.actor_integration_OptimisticLockingCheck() Delete Actor Response : " + deleteActor);

        }
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
    
    @Test
    public void crossFieldValidationCheck(){
         // Get Actor
        Response getResponse = null;
        try {
            getResponse = provider.target().path(FORWARD_SLASH + ACTORS_PATH + FORWARD_SLASH + 1).request(APPLICATION_JSON).get();
        } finally {
            System.out.println("Server responded ? " + (getResponse != null));
            assumeThat(getResponse, is(notNullValue()));
        }
        
        // post with invalid input
        final String fn = "Joh";
        final String ln = "Doe";

        // Post Actor
        Response postResponse = provider.target().path(FORWARD_SLASH + ACTORS_PATH).request(APPLICATION_JSON).post(Entity.json(createActor(fn, ln)));
        assertThat(postResponse, is(clientError()));
        
        final MultivaluedMap<String, Object> postResponseHeaders = postResponse.getHeaders();
            postResponseHeaders.forEach((key, value) -> {
                System.out.println("crossFieldValidationCheck() : key : " + key + ", value : " + value);
            });
            
        assertThat(postResponse.getHeaderString("validation-exception"), is("true"));
        
    }
    
     public static Matcher<Response> clientError() {
        return new CustomMatcher<Response>("is 4xx family of response") {

            @Override
            public boolean matches(Object o) {
                return (o instanceof Response)
                        && (((Response) o).getStatusInfo().getFamily() == Response.Status.Family.CLIENT_ERROR);
            }
        };
    }
}
