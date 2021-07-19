/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.movie.moviestore.sessionbean;

import com.movie.moviestore.entity.Movie;
import com.movie.moviestore.utils.AppConstants;
import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.apache.commons.lang3.RandomStringUtils;

/**
 *
 * @author DATA INTEGRATED
 */
@Stateless
public class MovieSessionBean {

    private static final Logger LOG = Logger.getLogger(MovieSessionBean.class.getName());

    @PersistenceContext(unitName = "com.movie_moviestore_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    public List<Movie> getAllMovies() {
        List<Movie> mvs = em.createNamedQuery("Movie.findAll", Movie.class)
                .getResultList();
        return mvs;
    }
    
    public Movie getById (Integer id) {
        Movie mvie = em.createNamedQuery("Movie.findById", Movie.class)
                .setParameter("id", id)
                .getSingleResult();
        return mvie;
    }

    public Movie getByMovieId(String movieid) {
        Movie movie = em.createNamedQuery("Movie.findByMovieId", Movie.class)
                .setParameter("movieId", movieid)
                .getSingleResult();
        return movie;
    }
    
    public Movie getByName (String name) {
        Movie mvie = em.createNamedQuery("Movie.findByName", Movie.class)
                .setParameter("name", name)
                .getSingleResult();
        return mvie;
    }
    
    public List<Movie> getByType (String type) {
        List<Movie> mvies = em.createNamedQuery("Movie.findByType", Movie.class)
                .setParameter("type", type)
                .getResultList();
        return mvies;
    }
    
    public List<Movie> getByStatus(String status) {
        List<Movie> mvies = em.createNamedQuery("Movie.findByStatus", Movie.class)
                .setParameter("status", status)
                .getResultList();
        return mvies;
    }
    
    public List<Movie> getByProduction (String production) {
        List<Movie> mvies = em.createNamedQuery("Movie.findByProduction", Movie.class)
                .setParameter("production", production)
                .getResultList();
        return  mvies;
    }

    public void updateMovie(Movie movie) {
        em.merge(movie);
    }

    public Response addMovie(Movie movie) {
        if (movie == null) {
            return errorResponse("Missing information!");
        }

        try {
            movie.setMovieId(AppConstants.TAG + RandomStringUtils.randomAlphanumeric(6));
            movie.setDatecreated(Date.from(Instant.now()));
            //movie.setMovieId(RandomStringUtils.randomAlphanumeric(10));
            em.persist(movie);
        } catch (Exception e) {
            LOG.log(Level.SEVERE, e.getMessage(), e);
            return errorResponse("Error occured!");
        }

        // movie.setMovieId(RandomStringUtils.randomAlphabetic(6).toUpperCase());
        //  movie.setDate(Date.from(Instant.MIN));
        // em.persist(movie);
        return null;
    }

    public void deleteMovie(Movie movie) {
        if (!em.contains(movie)) {
            movie = em.merge(movie);
        }

        em.remove(movie);
    }

    public Response errorResponse(String error) {
        return Response.status(Response.Status.BAD_REQUEST).entity(String.format("{\"message\" : \"%s\"}", error)).type(MediaType.APPLICATION_JSON).build();
    }
}
