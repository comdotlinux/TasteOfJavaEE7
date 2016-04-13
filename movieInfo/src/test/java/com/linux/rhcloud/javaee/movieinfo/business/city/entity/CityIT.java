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

package com.linux.rhcloud.javaee.movieinfo.business.city.entity;
import com.airhacks.rulz.em.EntityManagerProvider;
import static com.linux.rhcloud.javaee.movieinfo.business.city.entity.City.GET_ALL_CITIES;
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
public class CityIT {

    @Rule
    public EntityManagerProvider provider = EntityManagerProvider.persistenceUnit("it");

    @Before
    public void setUp() {
        EntityManagerProvider prov = EntityManagerProvider.persistenceUnit("it");
        prov.tx().begin();
        Query truncateTable = prov.em().createNativeQuery("TRUNCATE TABLE CITY");
        int executeUpdate = truncateTable.executeUpdate();
        prov.em().flush();
        prov.tx().commit();
        System.out.println("CityIT.setUp() : truncate output " + executeUpdate);
        
        provider.tx().begin();
    }
    
    @After
    public void tearDown() {
        provider.em().flush();
        provider.tx().commit();
    }

    @Test
    public void crudCity() {
        City city = new City();
        city.setCity("Bhusawal");
        city.setCountryId(44L);

        City actual = provider.em().merge(city);

        assertThat(actual.getId(), is(notNullValue()));
        
        City findActual = provider.em().find(City.class, actual.getId());
        
        assertThat(findActual.getCity(), is(equalTo(actual.getCity())));
        
        provider.em().remove(findActual);
        
    }

    @Test
    public void retrieveAllCities() {
        City city1 = new City();
        city1.setCity("Bhusawal");
        city1.setCountryId(44L);

        City city2 = new City();
        city2.setCity("Pune");
        city2.setCountryId(44L);

        City city3 = new City();
        city3.setCity("Mumbai");
        city3.setCountryId(44L);

        City city4 = new City();
        city4.setCity("Chennai");
        city4.setCountryId(44L);

        List<City> expectedCityList = Arrays.asList(city1, city2, city3, city4);
        expectedCityList.stream().forEach((c) -> {
            provider.em().merge(c);
        });
        
        List<City> allCities = provider.em().createNamedQuery(GET_ALL_CITIES, City.class).getResultList();

        assertThat(allCities.size(), is(expectedCityList.size()));
    }
    
}
