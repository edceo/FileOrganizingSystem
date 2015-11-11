/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.edsoft.teknosaproject.bean;

import com.edsoft.teknosaproject.entity.Person;
import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author Yusuf ÖNDER
 */
@ManagedBean(name = "login")
@SessionScoped
public class LoginBean implements Serializable {

    private Person person = new Person();
    private String username;
    private String password;

    public LoginBean() {
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
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

    public boolean onSession() {
        return null != FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get(person.getUsername());
    }

    public String welcomeScreen() {
        return "Hoş geldiniz " + person.getMyname() + " Bey";
    }

    public String logOut() {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "index?faces-redirect=true";
    }

    public String login() {
        EntityManager em = Persistence.createEntityManagerFactory("teknosa").createEntityManager();
        Query query = em.createNamedQuery("Person.findByUsername");
        query.setParameter("username", username);
        if(0 == query.getResultList().size()) {
            em.close();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "HATA", "Bu kişi kayıtlı değil"));
            person = new Person();
            return "index?faces-redirect=true";
        }
        person = (Person) query.getResultList().get(0);
        if(person.getPassword().equals(password)) {
            em.close();
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put(person.getUsername(), person);
            return "anasayfa?faces-redirect=true";
        }
        person = new Person();
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "HATA", "Şifre yanlış"));
        return "index?faces-redirect=true";
    }
}
