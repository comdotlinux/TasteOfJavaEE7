/*
 * 
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
 * 
 */
package com.linux.rhcloud.javaee.movieinfo.business.address.entity;

import static com.linux.rhcloud.javaee.movieinfo.business.address.entity.Address.GET_ALL_ADDRESSES;
import com.linux.rhcloud.javaee.movieinfo.business.city.entity.City;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Guruprasad Kulkarni <guru@linux.com>
 */
@Entity(name = "address")
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@NamedQueries(@NamedQuery(name = GET_ALL_ADDRESSES, query = "SELECT a FROM address a"))
public class Address implements Serializable {

    private static final long serialVersionUID = -3719903575834930580L;
    
     private static final String PREFIX = "movieinfo.business.address.entity.";

    /**
     * Named Query :: {@value #GET_ALL_ADDRESSES} to get all actors
     */
    public static final String GET_ALL_ADDRESSES = PREFIX + "findAll";

    @Id
    @GeneratedValue
    @Column(name = "address_id")
    private Long id;
    
    private String address;
    
    private String address2;
    
    private String district;
    
    @OneToOne
    private City city;
    
    private String postalCode;
    
    private String phone;
    
    @Column(name = "last_update")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastUpdate;
 
    @Lob
    private byte[] location;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Date getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Date lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public byte[] getLocation() {
        return location;
    }

    public void setLocation(byte[] location) {
        this.location = location;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }
    
}
