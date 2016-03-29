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
package com.linux.rhcloud.javaee.movieinfo.business.actor.entity;

import com.linux.rhcloud.javaee.movieinfo.crosscutting.entityvalidation.CrossFieldCheck;
import com.linux.rhcloud.javaee.movieinfo.crosscutting.entityvalidation.CrossFieldEntityValidator;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.ToStringBuilder;
import static org.apache.commons.lang3.builder.ToStringStyle.SHORT_PREFIX_STYLE;

/**
 * Actor Entity
 *
 * @author Guruprasad Kulkarni <guru@linux.com>
 */
@Entity(name = "actor")
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@NamedQueries(@NamedQuery(name = Actor.FIND_ALL_ACTORS, query = "SELECT a FROM actor a"))
@CrossFieldCheck(message = "If Actor firstname is 3 characters then lastname must be greater than 3 characters.")
public class Actor implements Serializable, CrossFieldEntityValidator {

    private static final long serialVersionUID = -8357357239161127772L;

    private static final String PREFIX = "movieinfo.business.entity.actor.";

    /**
     * Named Query :: {@value #FIND_ALL_ACTORS} to get all actors
     */
    public static final String FIND_ALL_ACTORS = PREFIX + "findAll";

    /** The column name of Actor id: {@value #ID} */
    public static final String ID = "actor_id";
    
    /** The column name of Actor first name: {@value #FIRST_NAME} */
    public static final String FIRST_NAME = "first_name";
    
    /** The column name of Actor last name: {@value #LAST_NAME} */
    public static final String LAST_NAME = "last_name";
    
    /** The column name of Actor last update: {@value #LAST_UPDATE} */
    public static final String LAST_UPDATE = "last_update";

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = ID)
    private long id;

    @Column(name = FIRST_NAME)
    @Size(min = 3, max = 45)
    private String firstname;

    @Column(name = LAST_NAME)
    @Size(max = 45)
    private String lastname;

    @Column(name = LAST_UPDATE)
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastUpdateDate;
    
    @Version
    private long version;


    public Actor(String firstname, String lastname) {
        this.firstname = firstname;
        this.lastname = lastname;
    }

    public Actor() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public Date getLastUpdateDate() {
        return lastUpdateDate;
    }

    public void setLastUpdateDate(Date lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
    }

    public long getVersion() {
        return version;
    }

    public void setVersion(long version) {
        this.version = version;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return new ToStringBuilder(this, SHORT_PREFIX_STYLE)
                .append(id)
                .append(firstname)
                .append(lastname)
                .append(lastUpdateDate)
                .append(version)
                .toString();
    }

    @Override
    public boolean isValid() {
        return StringUtils.length(firstname) >= 4 || StringUtils.length(lastname) > 3;
    }

}
