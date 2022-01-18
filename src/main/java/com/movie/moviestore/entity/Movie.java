/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.movie.moviestore.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Null;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author kioko
 */
@Entity
@Table(name = "movie")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Movie.findAll", query = "SELECT m FROM Movie m")
    , @NamedQuery(name = "Movie.findById", query = "SELECT m FROM Movie m WHERE m.id = :id")
    , @NamedQuery(name = "Movie.findByMovieId", query = "SELECT m FROM Movie m WHERE m.movieId = :movieId")
    , @NamedQuery(name = "Movie.findByName", query = "SELECT m FROM Movie m WHERE m.name = :name")
    , @NamedQuery(name = "Movie.findByType", query = "SELECT m FROM Movie m WHERE m.type = :type")
    , @NamedQuery(name = "Movie.findByDescription", query = "SELECT m FROM Movie m WHERE m.description = :description")
    , @NamedQuery(name = "Movie.findByProduction", query = "SELECT m FROM Movie m WHERE m.production = :production")
    , @NamedQuery(name = "Movie.findByStatus", query = "SELECT m FROM Movie m WHERE m.status = :status")})
public class Movie implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    
    @Null(message = "Name cannot be null")
    @Size(max = 50)
    @Column(name = "name")
    private String name;
    
    @Null(message = "Type cannot be null")
    @Size(max = 50)
    @Column(name = "type")
    private String type;
    
    @Null(message = "Description cannot be null")
    @Size(max = 50)
    @Column(name = "description")
    private String description;
    
    @Null(message = "Production cannot be null")
    @Size(max = 50)
    @Column(name = "production")
    private String production;
    
    @Null(message = "Status cannot be null")
    @Size(max = 20)
    @Column(name = "status")
    private String status;
    
    @Column(name = "datecreated")
    @Temporal(TemporalType.TIMESTAMP)
    private Date datecreated;
    @Size(max = 50)
    @Column(name = "movieId")
    private String movieId;
   
    public Movie() {
    }

    public Date getDatecreated() {
        return datecreated;
    }

    public void setDatecreated(Date datecreated) {
        this.datecreated = datecreated;
    }
    
    public Movie(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getProduction() {
        return production;
    }

    public void setProduction(String production) {
        this.production = production;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMovieId() {
        return movieId;
    }

    public void setMovieId(String movieId) {
        this.movieId = movieId;
    }
    
    

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        
        if (!(object instanceof Movie)) {
            return false;
        }
        Movie other = (Movie) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.movie.moviestore.entity.Movie[ id=" + id + " ]";
    }
    
}
