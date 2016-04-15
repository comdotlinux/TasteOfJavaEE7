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
package com.linux.rhcloud.javaee.movieinfo.business.country;

import com.linux.rhcloud.javaee.movieinfo.business.city.entity.*;
import com.airhacks.rulz.em.EntityManagerProvider;
import static com.linux.rhcloud.javaee.movieinfo.business.country.Country.GET_ALL_COUNTRIES;
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
public class CountryIT {

    @Rule
    public EntityManagerProvider provider = EntityManagerProvider.persistenceUnit("it");

    @Before
    public void setUp() {
        EntityManagerProvider prov = EntityManagerProvider.persistenceUnit("it");
        prov.tx().begin();

        Query truncateTableCountry = prov.em().createNativeQuery("DELETE FROM COUNTRY");
        int executeUpdate = truncateTableCountry.executeUpdate();
        System.out.println("CityIT.setUp() : truncate country output " + executeUpdate);

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
    public void crudCountry() {
        Country country = new Country();
        country.setCountry("US");

        Country actual = provider.em().merge(country);

        assertThat(actual.getId(), is(notNullValue()));

        Country findActual = provider.em().find(Country.class, actual.getId());

        assertThat(findActual.getCountry(), is(equalTo(actual.getCountry())));

        provider.em().remove(findActual);

    }

    @Test
    public void retrieveAllCountries() {
        Country country1 = new Country();
        country1.setCountry("INDIA");

        Country country2 = new Country();
        country2.setCountry("INDIA");

        Country country3 = new Country();
        country3.setCountry("INDIA");

        Country country4 = new Country();
        country4.setCountry("INDIA");

        Country country5 = new Country();
        country5.setCountry("INDIA");

        List<Country> expectedCityList = Arrays.asList(country1, country2, country3, country4, country5);
        expectedCityList.stream().forEach((c) -> {
            provider.em().merge(c);
        });

        List<Country> allCities = provider.em().createNamedQuery(GET_ALL_COUNTRIES, Country.class).getResultList();

        assertThat(allCities.size(), is(expectedCityList.size()));
    }

}
