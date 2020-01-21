/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.movie.moviestore.service;

import com.movie.moviestore.entity.Movie;
import com.movie.moviestore.sessionbean.MovieSessionBean;
import java.util.List;
import javax.ejb.EJB;
import javax.persistence.EntityManager;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


/**
 *
 * @author DATA INTEGRATED
 */
@Path("store")
public class MovieResource {
    
    @EJB
    private MovieSessionBean msb;
    
    @EJB
    private EntityManager em;
    
    
    @GET
    @Path("list")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Movie> getAllMovies(){
        return msb.getAllMovies();
                
    }
    
    
}
