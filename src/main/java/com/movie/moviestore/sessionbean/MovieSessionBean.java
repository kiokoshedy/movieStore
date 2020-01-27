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
    
    @PersistenceContext
    private EntityManager em;

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    
    public List<Movie> getAllMovies() {
        List<Movie> mvs = em.createNamedQuery("Movie.findAll", Movie.class)
                .getResultList();
        return mvs;
    }

    public List<Movie> getAllMovies(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
