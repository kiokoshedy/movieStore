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
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author DATA INTEGRATED
 */

@Path("/store")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class MovieResource {
    
    @EJB
    private MovieSessionBean msb;
    
    @PersistenceContext(unitName = "com.movie_moviestore_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @GET
    @Path("/list")
    public List<Movie> getAllMovies (){
        
        return msb.getAllMovies();
    }
    
    @GET
    @Path("{id}")
    public Movie getMovie (@PathParam("id") int id) {
        Movie movie = msb.getById(id);
        return movie;
        
    }
    
    @DELETE
    @Path("{id}")
    public void deleteMovie(@PathParam("id") int id) {
        Movie movie = msb.getById(id);
        msb.deleteMovie(movie);
                
    }
    @PUT
    @Path("{id}")
    public Response updateMovie (@PathParam("id") int id, Movie movie) {
        Movie update = msb.getById(id);
        
        update.setName(movie.getName());
        update.setDescription(movie.getDescription());
        update.setProduction(movie.getProduction());
        update.setType(movie.getType());
        update.setStatus(movie.getStatus());
        
        msb.updateMovie(update);
        
        return Response.ok().build();
    }
    
    @POST
    public Response addMovies(Movie movie) {
        msb.addMovie(movie);
        
        return Response.ok().build();
    }
}
    
    