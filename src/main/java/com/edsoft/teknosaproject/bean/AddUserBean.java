/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.edsoft.teknosaproject.bean;

import com.edsoft.teknosaproject.entity.Person;
import com.edsoft.teknosaproject.entity.function.PersonCRUD;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author Yusuf ÖNDER
 */
@ManagedBean(name = "adduser")
@RequestScoped
public class AddUserBean implements PersonCRUD {

    private Person person = new Person();
    private String username;
    private String password;
    private String newPassword;
    private String myName;
    private String key;

    public AddUserBean() {
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

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getMyName() {
        return myName;
    }

    public void setMyName(String myName) {
        this.myName = myName;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    @Override
    public void addUser() {
        if (!key.equals("12011708")) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "HATA", "Anahtar yanlış girildi"));
            return;
        }
        if (!findUser()) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "HATA", "Bu kişi var"));
            return;
        }
        person.setUsername(username);
        person.setPassword(password);
        person.setMyname(myName);
        EntityManager em = Persistence.createEntityManagerFactory("teknosa").createEntityManager();
        em.getTransaction().begin();
        em.persist(person);
        em.getTransaction().commit();
        em.close();
        person = new Person();
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Başarılı", "Kayıt alındı"));

    }

    private boolean findUser() {
        EntityManager em = Persistence.createEntityManagerFactory("teknosa").createEntityManager();
        Query query = em.createNamedQuery("Person.findByUsername");
        query.setParameter("username", username);
        List<Person> list = query.getResultList();
        em.close();
        return 0 == list.size();
    }

    @Override
    public void changePassword() {
        if (!key.equals("12011708")) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "HATA", "Anahtarı Yanlış Girdiniz"));
            return;
        }
        if (findUser()) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "HATA", "Bu kişi bulunamadı"));
            return;
        }
        EntityManager em = Persistence.createEntityManagerFactory("teknosa").createEntityManager();
        em.getTransaction().begin();
        Query query = em.createNamedQuery("Person.findByUsername");
        query.setParameter("username", username);
        person = (Person) query.getResultList().get(0);
        person.setPassword(newPassword);
        em.merge(person);
        em.getTransaction().commit();
        em.close();
        person = new Person();
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Başarılı", "Şifre güncellendi"));
    }
}
