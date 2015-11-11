/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.edsoft.teknosaproject.bean;

import com.edsoft.teknosaproject.entity.Menus;
import com.edsoft.teknosaproject.menus.function.AjaxMenu;
import java.io.File;
import java.io.Serializable;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.TrueFileFilter;

/**
 *
 * @author Yusuf ÖNDER
 */
@ManagedBean(name = "rapor")
@ViewScoped
public class ReportBean implements Serializable, AjaxMenu {

    private String family;
    private String type;
    private String brand;
    private String document;
    private final String DIR = "D:";

    private Path path;

    private List<File> list;
    private List<File> filtered;
    private File[] fileList;

    private List<SelectItem> familyMenu;
    private List<SelectItem> typeMenu;
    private List<SelectItem> brandMenu;
    private List<SelectItem> documentMenu;

    @PostConstruct
    public void initialize() {
        list = new ArrayList<>();
        filtered = new ArrayList<>();
        Menus menus;
        familyMenu = new ArrayList<>();
        typeMenu = new ArrayList<>();
        brandMenu = new ArrayList<>();
        documentMenu = new ArrayList<>();
        path = Paths.get(DIR, "AnaDepo");
        //path = Paths.get("D:", "Teknosa");
        fileList = path.toFile().listFiles();
        EntityManager em = Persistence.createEntityManagerFactory("teknosa").createEntityManager();
        em.getTransaction().begin();
        Query query = em.createNamedQuery("Menus.findByValued");
        familyMenu.add(new SelectItem("", "Seçiniz"));
        for (File file : fileList) {
            if (file.isDirectory()) {
                query.setParameter("valued", file.getName());
                menus = (Menus) query.getResultList().get(0);
                menus.setValue(menus.getValued());
                menus.setLabel(menus.getLabel1());
                familyMenu.add(menus);
            }
        }
        em.getTransaction().commit();
        em.close();
    }

    public String getFamily() {
        return family;
    }

    public void setFamily(String family) {
        this.family = family;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getDocument() {
        return document;
    }

    public void setDocument(String document) {
        this.document = document;
    }

    public List<File> getList() {
        return list;
    }

    public void setList(List<File> list) {
        this.list = list;
    }

    public List<SelectItem> getFamilyMenu() {
        return familyMenu;
    }

    public void setFamilyMenu(List<SelectItem> familyMenu) {
        this.familyMenu = familyMenu;
    }

    public List<SelectItem> getTypeMenu() {
        return typeMenu;
    }

    public void setTypeMenu(List<SelectItem> typeMenu) {
        this.typeMenu = typeMenu;
    }

    public List<SelectItem> getBrandMenu() {
        return brandMenu;
    }

    public void setBrandMenu(List<SelectItem> brandMenu) {
        this.brandMenu = brandMenu;
    }

    public List<SelectItem> getDocumentMenu() {
        return documentMenu;
    }

    public void setDocumentMenu(List<SelectItem> documentMenu) {
        this.documentMenu = documentMenu;
    }

    public List<File> getFiltered() {
        return filtered;
    }

    public void setFiltered(List<File> filtered) {
        this.filtered = filtered;
    }

    public String reportDirectory() {
        path = Paths.get(DIR, "AnaDepo", family, type, brand, document);
        //path = Paths.get("D:", "Teknosa", family, type, brand, document);
        list = (List<File>) FileUtils.listFiles(path.toFile(), TrueFileFilter.INSTANCE, TrueFileFilter.INSTANCE);
        ConnectBean.list = list;
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(path.toString()));
        return "rapor2";
    }

    @Override
    public void typeListener() {
        Menus menus;
        typeMenu.clear();
        brandMenu.clear();
        documentMenu.clear();

        path = Paths.get(DIR, "AnaDepo", family);
        //path = Paths.get("D:", "Teknosa", family);
        fileList = path.toFile().listFiles();
        EntityManager em = Persistence.createEntityManagerFactory("teknosa").createEntityManager();
        em.getTransaction().begin();
        Query query = em.createNamedQuery("Menus.findByValued");
        typeMenu.add(new SelectItem("", "Seçiniz"));
        for (File file : fileList) {
            if (file.isDirectory()) {
                query.setParameter("valued", file.getName());
                menus = (Menus) query.getResultList().get(0);
                menus.setLabel(menus.getLabel1());
                menus.setValue(menus.getValued());
                typeMenu.add(menus);
            }
        }
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public void brandListener() {
        Menus menus;
        brandMenu.clear();
        documentMenu.clear();
        path = Paths.get(DIR, "AnaDepo", family, type);
        //path = Paths.get("D:", "Teknosa", family, type);
        fileList = path.toFile().listFiles();
        EntityManager em = Persistence.createEntityManagerFactory("teknosa").createEntityManager();
        em.getTransaction().begin();
        Query query = em.createNamedQuery("Menus.findByValued");
        brandMenu.add(new SelectItem("", "Seçiniz"));
        for (File file : fileList) {
            if (file.isDirectory()) {
                query.setParameter("valued", file.getName());
                menus = (Menus) query.getResultList().get(0);
                menus.setLabel(menus.getLabel1());
                menus.setValue(menus.getValued());
                brandMenu.add(menus);
            }
        }
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public void documentListener() {
        Menus menus;
        documentMenu.clear();
        path = Paths.get(DIR, "AnaDepo", family, type, brand);
        //path = Paths.get("D:", "Teknosa", family, type, brand);
        fileList = path.toFile().listFiles();
        EntityManager em = Persistence.createEntityManagerFactory("teknosa").createEntityManager();
        em.getTransaction().begin();
        Query query = em.createNamedQuery("Menus.findByValued");
        documentMenu.add(new SelectItem("", "Seçiniz"));
        for (File file : fileList) {
            if (file.isDirectory()) {
                query.setParameter("valued", file.getName());
                menus = (Menus) query.getResultList().get(0);
                menus.setLabel(menus.getLabel1());
                menus.setValue(menus.getValued());
                documentMenu.add(menus);
            }
        }
        em.getTransaction().commit();
        em.close();
    }
}
