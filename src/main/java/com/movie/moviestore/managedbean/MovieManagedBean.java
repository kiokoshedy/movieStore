/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.movie.moviestore.managedbean;

import com.movie.moviestore.entity.Movie;
import com.movie.moviestore.sessionbean.MovieSessionBean;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.Dependent;
import javax.inject.Named;



/**
 *
 * @author DATA INTEGRATED
 */
@Named(value = "movieManagedBean")
@Dependent
public class MovieManagedBean implements Serializable{

    private List<Movie> movielist;
    @EJB
    private MovieSessionBean msb;
    /**
     * Creates a new instance of MovieManagedBean
     */
    public MovieManagedBean() {
    }

     
    @PostConstruct
    public void init(){
        setMovielist(msb.getAllMovies());
    }
    public List<Movie> getMovielist() {
        return movielist;
    }

    public void setMovielist(List<Movie> movielist) {
        this.movielist = movielist;
    }
    
}
