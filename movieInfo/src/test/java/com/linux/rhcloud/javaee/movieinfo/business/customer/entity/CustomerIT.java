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
package com.linux.rhcloud.javaee.movieinfo.business.customer.entity;

import com.airhacks.rulz.em.EntityManagerProvider;
import com.linux.rhcloud.javaee.movieinfo.business.address.entity.Address;
import static com.linux.rhcloud.javaee.movieinfo.business.address.entity.Address.GET_ALL_ADDRESSES;
import com.linux.rhcloud.javaee.movieinfo.business.city.entity.City;
import com.linux.rhcloud.javaee.movieinfo.business.country.Country;
import java.util.Arrays;
import java.util.List;
import javax.persistence.Query;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import org.junit.After;
import static org.junit.Assert.assertThat;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

/**
 *
 * @author Guruprasad Kulkarni <guru@linux.com>
 */
public class CustomerIT {

    @Rule
    public EntityManagerProvider provider = EntityManagerProvider.persistenceUnit("it");

    @Before
    public void setUp() {
        EntityManagerProvider prov = EntityManagerProvider.persistenceUnit("it");
        prov.tx().begin();

        Query truncateTable = prov.em().createNativeQuery("DELETE FROM CUSTOMER");
        int executeUpdate = truncateTable.executeUpdate();
        System.out.println("AddressIT.setUp() : truncate Customer output " + executeUpdate);

        truncateTable = prov.em().createNativeQuery("DELETE FROM ADDRESS");
        executeUpdate = truncateTable.executeUpdate();
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
    public void saveCustomer() {
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
        
        Customer customer = new Customer();
        customer.setStoreId(Long.MIN_VALUE);
        customer.setAddress(address);
        customer.setFirstname("John");
        customer.setLastname("Jonzz");
        customer.setEmail("john.jonezz@deo.com");
        customer.setActive(1);
        

        Customer actual = provider.em().merge(customer);

        assertThat(actual.getId(), is(notNullValue()));
        
        Customer findActual = provider.em().find(Customer.class, actual.getId());
        
        assertThat(findActual.getEmail(), is(equalTo(actual.getEmail())));
        
        provider.em().remove(findActual);
        
    }
}
