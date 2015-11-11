/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.edsoft.teknosaproject.bean;

import com.edsoft.teknosaproject.entity.Menus;
import java.io.IOException;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;
import org.primefaces.event.SelectEvent;

/**
 *
 * @author Yusuf ÖNDER
 */
@ManagedBean(name = "addMenuBean")
@ViewScoped
public class AddMenuBean implements Serializable {

    private String familyLabel;
    private String familyId;
    private String typeLabel;
    private String typeId;
    private String brandLabel;
    private String brandId;
    private String documentLabel;
    private String documentId;
    private Path path;

    private List<Menus> list = new ArrayList<>();

    @PostConstruct
    public void initialize() {
        EntityManager em = Persistence.createEntityManagerFactory("teknosa").createEntityManager();
        em.getTransaction().begin();
        Query qry = em.createNamedQuery("Menus.findAll");
        list = qry.getResultList();
        em.getTransaction().commit();
        em.close();
    }

    public String getFamilyLabel() {
        return familyLabel;
    }

    public void setFamilyLabel(String familyLabel) {
        this.familyLabel = familyLabel;
    }

    public String getFamilyId() {
        return familyId;
    }

    public void setFamilyId(String familyId) {
        this.familyId = familyId;
    }

    public String getTypeLabel() {
        return typeLabel;
    }

    public void setTypeLabel(String typeLabel) {
        this.typeLabel = typeLabel;
    }

    public String getTypeId() {
        return typeId;
    }

    public void setTypeId(String typeId) {
        this.typeId = typeId;
    }

    public String getBrandLabel() {
        return brandLabel;
    }

    public void setBrandLabel(String brandLabel) {
        this.brandLabel = brandLabel;
    }

    public String getBrandId() {
        return brandId;
    }

    public void setBrandId(String brandId) {
        this.brandId = brandId;
    }

    public String getDocumentLabel() {
        return documentLabel;
    }

    public void setDocumentLabel(String documentLabel) {
        this.documentLabel = documentLabel;
    }

    public String getDocumentId() {
        return documentId;
    }

    public void setDocumentId(String documentId) {
        this.documentId = documentId;
    }

    public List<String> autoFamily(String query) {
        List<String> liste = new ArrayList<>();
        for (Menus menu : list) {
            if (menu.getLabel1().toLowerCase().startsWith(query.toLowerCase())) {
                liste.add(menu.getLabel1());
            }
        }
        return liste;
    }

    public void selectFamily(SelectEvent event) {
        for (Menus menu : list) {
            if (menu.getLabel1().equals(familyLabel)) {
                familyId = menu.getValued();
            }
        }
    }

    public List<String> auto1Types(String query) {
        List<String> liste = new ArrayList<>();
        for (Menus menu : list) {
            if (menu.getLabel1().toLowerCase().startsWith(query.toLowerCase())) {
                liste.add(menu.getLabel1());
            }
        }
        return liste;
    }

    public void selectTypes(SelectEvent event) {
        for (Menus menu : list) {
            if (menu.getLabel1().equals(typeLabel)) {
                typeId = menu.getValued();
            }
        }
    }

    public List<String> autoBrand(String query) {
        List<String> liste = new ArrayList<>();
        for (Menus menu : list) {
            if (menu.getLabel1().toLowerCase().startsWith(query.toLowerCase())) {
                liste.add(menu.getLabel1());
            }
        }
        return liste;
    }

    public void selectBrand(SelectEvent event) {
        for (Menus menu : list) {
            if (menu.getLabel1().equals(brandLabel)) {
                brandId = menu.getValued();
            }
        }
    }

    public List<String> autoDocument(String query) {
        List<String> liste = new ArrayList<>();
        for (Menus menu : list) {
            if (menu.getLabel1().toLowerCase().startsWith(query.toLowerCase())) {
                liste.add(menu.getLabel1());
            }
        }
        return liste;
    }

    public void selectDocument(SelectEvent event) {
        for (Menus menu : list) {
            if (menu.getLabel1().equals(documentLabel)) {
                documentId = menu.getValued();
            }
        }
    }

    public void addMenu() {
        path = Paths.get("D:", "AnaDepo", familyId, typeId, brandId, documentId);
        if (!Files.isDirectory(path)) {
            try {
                Files.createDirectories(path);
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "BAŞARILI", "Klasör Dizini Oluşturuldu"));
            } catch (IOException ex) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "HATA", "Klasör Dizini Oluşturulamadı"));
                return;
            }
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "HATA", "Dizin mevcut"));
            return;
        }
        EntityManager em = Persistence.createEntityManagerFactory("teknosa").createEntityManager();
        em.getTransaction().begin();
        Query q = em.createNamedQuery("Menus.findByValued");
        q.setParameter("valued", familyId);
        if (0 == q.getResultList().size()) {
            em.persist(new Menus(familyId, familyLabel));
        }
        q.setParameter("valued", typeId);
        if (0 == q.getResultList().size()) {
            em.persist(new Menus(typeId, typeLabel));
        }
        q.setParameter("valued", brandId);
        if (0 == q.getResultList().size()) {
            em.persist(new Menus(brandId, brandLabel));
        }
        q.setParameter("valued", documentId);
        if (0 == q.getResultList().size()) {
            em.persist(new Menus(documentId, documentLabel));
        }
        em.getTransaction().commit();
        em.close();
    }
}
