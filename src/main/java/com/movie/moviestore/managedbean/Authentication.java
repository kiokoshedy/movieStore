/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.movie.moviestore.managedbean;

import com.github.adminfaces.template.session.AdminSession;
import java.io.IOException;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.inject.Named;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author DATA INTEGRATED
 */
@ManagedBean
@Named(value = "authentication")
@SessionScoped
public class Authentication implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private String username = "username";
    private String password = "password";
    
    private static final Logger LOG = Logger.getLogger(Authentication.class.getName());

    @Inject
    AdminSession as;

    public void login() throws IOException {
        FacesContext context = FacesContext.getCurrentInstance();
        ExternalContext externalContext = context.getExternalContext();
        HttpServletRequest request = (HttpServletRequest) externalContext.getRequest();

        try {
            request.login(username, password);
            as.setIsLoggedIn(Boolean.TRUE);
            as.setUserRedirected(Boolean.FALSE);
            setUsername(getUsername());
            setPassword(getPassword());
            String url = String.format("%s/%s", externalContext.getRequestContextPath(), "/index.xhtml");
            System.out.println("ok " + url);
            externalContext.redirect(url);
        } catch (ServletException e) {
            // Handle unknown username/password in request.login().
            System.out.println(e.getMessage());
            LOG.log(Level.SEVERE, e.getMessage(), e);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Warning! Login failed", ""));
        }
    }

    public void logout() throws IOException {
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        externalContext.invalidateSession();
        String url = String.format("%s/%s", externalContext.getRequestContextPath(), "login.xhtml");
        externalContext.redirect(url);
    }

    /*public void login() throws IOException{
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
    }*/
    
    @PostConstruct
    public void init() {
        as.setIsLoggedIn(Boolean.FALSE);
        as.setUserRedirected(Boolean.TRUE);
    }

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

}
