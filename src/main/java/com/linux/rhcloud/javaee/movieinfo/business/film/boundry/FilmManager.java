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
}
