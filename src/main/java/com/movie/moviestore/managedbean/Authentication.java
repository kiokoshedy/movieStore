/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.movie.moviestore.managedbean;

import com.github.adminfaces.template.session.AdminSession;
import java.io.IOException;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.RequestScoped;
import javax.inject.Named;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import org.omnifaces.util.Faces;
import org.primefaces.PrimeFaces;

/**
 *
 * @author DATA INTEGRATED
 */
@Named(value = "authentication")
@RequestScoped
public class Authentication implements Serializable {
    private String username;
     
    private String password;
    
    @Inject
    AdminSession as;
 
    public String getUsername() {
        return username;
    }
 
    public void setUsername(String username) {
        this.username = username;
    }
 
    public String getPassword() {
        return password;
    }
 
    public void setPassword(String password) {
        this.password = password;
    }
   
    public void login() throws IOException{
        FacesMessage message = null;
        boolean loggedIn = false;
         
        if(username != null && username.equals("admin") && password != null && password.equals("admin")) {
            loggedIn = true;
            Faces.redirect("index.xhtml");
            System.out.println("ok");
        } else {
            loggedIn = false;
            message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Loggin Error", "Invalid credentials");
        }
        
        FacesContext.getCurrentInstance().addMessage(null, message);
        PrimeFaces.current().ajax().addCallbackParam("loggedIn", loggedIn);
    }
    
    @PostConstruct
    public void init() {
        as.setIsLoggedIn(Boolean.FALSE);
        as.setUserRedirected(Boolean.TRUE);
    }
}
   