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
package com.linux.rhcloud.javaee.movieinfo.business.film.boundry;

import com.linux.rhcloud.javaee.movieinfo.business.film.entity.Film;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author guru
 */
@Stateless
public class FilmManager {
    
    @PersistenceContext
    private EntityManager em;
    
    public List<Film> all(){
        return em.createNamedQuery(Film.FIND_ALL_FILMS, Film.class).getResultList();
    }
    
    public Film findById(long id){
        return em.find(Film.class, id);
    }
    
    public Film updateFilm(Film film){
        return this.em.merge(film);
    }
    
    public Film save(Film film){
        return updateFilm(film);
    }
}
