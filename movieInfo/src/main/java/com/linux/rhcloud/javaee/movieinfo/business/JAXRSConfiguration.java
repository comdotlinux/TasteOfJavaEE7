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

import static com.linux.rhcloud.javaee.movieinfo.business.JAXRSConfiguration.JAXRS_BASE;
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
