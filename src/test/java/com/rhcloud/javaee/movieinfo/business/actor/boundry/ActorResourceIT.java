/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rhcloud.javaee.movieinfo.business.actor.boundry;

import com.airhacks.rulz.jaxrsclient.JAXRSClientProvider;
import static com.linux.rhcloud.javaee.movieinfo.business.actor.boundry.ActorResource.ACTORS_PATH;
import static com.linux.rhcloud.javaee.movieinfo.business.actor.boundry.JAXRSConfiguration.JAXRS_BASE;
import java.util.ResourceBundle;
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
    
    private static final String URI = JAXRS_BASE + "/" + ACTORS_PATH;
    
    @Rule
    public JAXRSClientProvider provider = JAXRSClientProvider.buildWithURI(URI);
    
    
     @Test
     public void CrudForActorIntegrationTest() {
         
     }
}
