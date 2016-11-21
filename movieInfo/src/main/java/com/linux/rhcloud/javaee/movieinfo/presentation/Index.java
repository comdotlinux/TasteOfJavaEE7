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
        String actorView = "actors";
        LOG.info("redirecting to {}", actorView);
        return actorView;
    }
    
    public String getFilmView(){
         String filmView = "films";
        LOG.info("redirecting to {}", filmView);
        return filmView;
    }
}
