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
