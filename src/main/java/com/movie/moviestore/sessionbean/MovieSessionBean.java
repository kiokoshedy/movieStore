/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.movie.moviestore.sessionbean;

import com.movie.moviestore.entity.Movie;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author DATA INTEGRATED
 */
@Stateless
public class MovieSessionBean {
    
    @PersistenceContext(unitName = "com.movie_moviestore_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    
    public List<Movie> getAllMovies() {
        List<Movie> mvs = em.createNamedQuery("Movie.findAll", Movie.class)
                .getResultList();
        return mvs;
    }
    public Movie getById(Integer id) {
        return em.find(Movie.class, id);
    }
    
    public void updateMovie(Movie movie) {
        em.merge(movie);
    }
    
    public void addMovie (Movie movie) {
        em.persist(movie);
    }
    
    public void deleteMovie (Movie movie) {
         if (!em.contains(movie)) {
            movie = em.merge(movie);
        }

        em.remove(movie);
    }
}
