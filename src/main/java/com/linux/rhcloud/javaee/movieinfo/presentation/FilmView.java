package com.linux.rhcloud.javaee.movieinfo.presentation;

import com.linux.rhcloud.javaee.movieinfo.business.boundry.FilmManager;
import com.linux.rhcloud.javaee.movieinfo.business.entity.Film;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;

/**
 *
 * @author guru
 */
@Model
public class FilmView {

    private Film film;
    
    @Inject
    private FilmManager fm;
    
    @PostConstruct
    public void init(){
        this.film = new Film();
    }
    
    public List<Film> getAllFilms(){
        return fm.all();
    }
}
