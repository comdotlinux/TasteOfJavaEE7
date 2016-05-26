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

import com.airhacks.rulz.em.EntityManagerProvider;
import static com.linux.rhcloud.javaee.movieinfo.business.address.entity.Address.GET_ALL_ADDRESSES;
import com.linux.rhcloud.javaee.movieinfo.business.city.entity.City;
import com.linux.rhcloud.javaee.movieinfo.business.country.entity.Country;
import java.util.Arrays;
import java.util.List;
import javax.persistence.Query;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Rule;

/**
 *
 * @author Guruprasad Kulkarni <guru@linux.com>
 */
public class AddressIT {

    @Rule
    public EntityManagerProvider provider = EntityManagerProvider.persistenceUnit("it");

    @Before
    public void setUp() {
        EntityManagerProvider prov = EntityManagerProvider.persistenceUnit("it");
        prov.tx().begin();
        
        Query truncateTable = prov.em().createNativeQuery("DELETE FROM ADDRESS");
        int executeUpdate = truncateTable.executeUpdate();
        System.out.println("AddressIT.setUp() : truncate Address output " + executeUpdate);

        Query truncateTableCity = prov.em().createNativeQuery("DELETE FROM CITY");
        executeUpdate = truncateTableCity.executeUpdate();
        System.out.println("AddressIT.setUp() : truncate city output " + executeUpdate);
        
        Query truncateTableCountry = prov.em().createNativeQuery("DELETE FROM COUNTRY");
        executeUpdate = truncateTableCountry.executeUpdate();
        System.out.println("AddressIT.setUp() : truncate country output " + executeUpdate);
        
        prov.em().flush();
        prov.tx().commit();
        
        provider.tx().begin();
    }
    
    @After
    public void tearDown() {
        provider.em().flush();
        provider.tx().commit();
    }

    @Test
    public void saveAddress() {
        Country country = new Country();
        country.setCountry("US");
        
        City city = new City();
        city.setCity("NY");
        city.setCountry(country);
        
        Address address = new Address();
        address.setAddress("1121 North Main Street");
        address.setAddress2("Loja Avenue");
        address.setDistrict("California");
        address.setCity(city);
        address.setPostalCode("17886");
        address.setPhone("838635286649");

        Address actual = provider.em().merge(address);

        assertThat(actual.getId(), is(notNullValue()));
        
        Address findActual = provider.em().find(Address.class, actual.getId());
        
        assertThat(findActual.getAddress(), is(equalTo(actual.getAddress())));
        
        provider.em().remove(findActual);
        
    }

    @Test
    public void retrieveAddress() {
        Country country = new Country();
        country.setCountry("US");
        
        City city = new City();
        city.setCity("NY");
        city.setCountry(country);
        
        Address address1 = new Address();
        address1.setAddress("1121 North Main Street");
        address1.setAddress2("Loja Avenue");
        address1.setDistrict("California");
        address1.setCity(city);
        address1.setPostalCode("17886");
        address1.setPhone("838635286649");

        Address address2 = new Address();
        address2.setAddress("1121 North Main Street");
        address2.setAddress2("Loja Avenue");
        address2.setDistrict("California");
        address2.setCity(city);
        address2.setPostalCode("17886");
        address2.setPhone("838635286649");

        Address address3 = new Address();
        address3.setAddress("1121 North Main Street");
        address3.setAddress2("Loja Avenue");
        address3.setDistrict("California");
        address3.setCity(city);
        address3.setPostalCode("17886");
        address3.setPhone("838635286649");

        Address address4 = new Address();
        address4.setAddress("1121 North Main Street");
        address4.setAddress2("Loja Avenue");
        address4.setDistrict("California");
        address4.setCity(city);
        address4.setPostalCode("17886");
        address4.setPhone("838635286649");

        List<Address> expectedAdrList = Arrays.asList(address1, address2, address3, address4);
        expectedAdrList.stream().forEach((adr) -> {
            provider.em().merge(adr);
        });
        
        List<Address> allAddresses = provider.em().createNamedQuery(GET_ALL_ADDRESSES, Address.class).getResultList();

        assertThat(allAddresses.size(), is(expectedAdrList.size()));
    }
    
}
