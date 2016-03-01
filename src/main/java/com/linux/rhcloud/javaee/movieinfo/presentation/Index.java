package com.linux.rhcloud.javaee.movieinfo.presentation;

import javax.enterprise.inject.Model;
import org.slf4j.Logger;
import static org.slf4j.LoggerFactory.getLogger;

/**
 * Backing bean for Index page
 *
 * @author Guruprasad Kulkarni <guru@linux.com>
 */
@Model
public class Index {

    private static final Logger LOG = getLogger(Index.class);

    public String getActorView(){
        return "actorView";
    }
}
