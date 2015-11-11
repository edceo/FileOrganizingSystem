/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.edsoft.teknosaproject.bean;

import com.edsoft.teknosaproject.entity.Menus;
import com.edsoft.teknosaproject.menus.function.AjaxMenu;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.nio.file.FileSystems;
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
import javax.faces.model.SelectItem;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author Yusuf ÖNDER
 */
@ManagedBean(name = "upload")
@ViewScoped
public class FileUploadBean implements Serializable, AjaxMenu {

    private Path path;
    private String newFilePath;
    private String dirsName;

    private File[] fileList;

    private List<SelectItem> familyMenu;
    private List<SelectItem> typeMenu;
    private List<SelectItem> brandMenu;
    private List<SelectItem> documentMenu;

    private String selectedFamily;
    private String selectdType;
    private String selectedBrand;
    private String selectedDocument;
    
    private final String DIR = "D:";

    @PostConstruct
    public void initialize() {
        Menus menus;
        familyMenu = new ArrayList<>();
        typeMenu = new ArrayList<>();
        brandMenu = new ArrayList<>();
        documentMenu = new ArrayList<>();
        //path = Paths.get(DIR, "T_K_B AnaDepo");
        path = Paths.get(DIR, "AnaDepo");
        fileList = path.toFile().listFiles();
        EntityManager em = Persistence.createEntityManagerFactory("teknosa").createEntityManager();
        em.getTransaction().begin();
        Query query = em.createNamedQuery("Menus.findByValued");
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

    public String getNewFilePath() {
        return newFilePath;
    }

    public void setNewFilePath(String newFilePath) {
        this.newFilePath = newFilePath;
    }

    public File[] getFileList() {
        return fileList;
    }

    public void setFileList(File[] fileList) {
        this.fileList = fileList;
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

    public String getSelectedFamily() {
        return selectedFamily;
    }

    public void setSelectedFamily(String selectedFamily) {
        this.selectedFamily = selectedFamily;
    }

    public String getSelectdType() {
        return selectdType;
    }

    public void setSelectdType(String selectdType) {
        this.selectdType = selectdType;
    }

    public String getSelectedBrand() {
        return selectedBrand;
    }

    public void setSelectedBrand(String selectedBrand) {
        this.selectedBrand = selectedBrand;
    }

    public String getSelectedDocument() {
        return selectedDocument;
    }

    public void setSelectedDocument(String selectedDocument) {
        this.selectedDocument = selectedDocument;
    }

    @Override
    public void typeListener() {
        Menus menus;
        typeMenu.clear();
        brandMenu.clear();
        documentMenu.clear();
        path = Paths.get(DIR, "AnaDepo", selectedFamily);
        //path = Paths.get("D:", "Teknosa", selectedFamily);
        fileList = path.toFile().listFiles();
        EntityManager em = Persistence.createEntityManagerFactory("teknosa").createEntityManager();
        em.getTransaction().begin();
        Query query = em.createNamedQuery("Menus.findByValued");
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
        //path = Paths.get("E:", "T_K_B AnaDepo", selectedFamily, selectdType);
        path = Paths.get(DIR, "AnaDepo", selectedFamily, selectdType);
        fileList = path.toFile().listFiles();
        EntityManager em = Persistence.createEntityManagerFactory("teknosa").createEntityManager();
        em.getTransaction().begin();
        Query query = em.createNamedQuery("Menus.findByValued");
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
        path = Paths.get(DIR, "AnaDepo", selectedFamily, selectdType, selectedBrand);
        //path = Paths.get("D:", "Teknosa", selectedFamily, selectdType, selectedBrand);
        fileList = path.toFile().listFiles();
        EntityManager em = Persistence.createEntityManagerFactory("teknosa").createEntityManager();
        em.getTransaction().begin();
        Query query = em.createNamedQuery("Menus.findByValued");
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

    public void createDirs() {
        if (!Files.isDirectory(path)) {
            try {
                Files.createDirectories(path);
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "BAŞARILI", "Klasör Dizini Oluşturuldu"));
            } catch (IOException ex) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "HATA", "Dizin oluşturulamadı"));
            }
        }
        dirsName = selectedFamily + selectdType + selectedBrand + selectedDocument;
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "BAŞARILI", "Mevcut Dizin Seçildi"));
    }

    public void handleFileUpload(FileUploadEvent event) {
        if ((selectedFamily == null) || (selectdType == null) || (selectedBrand == null || (selectedDocument == null))) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "HATA", "Menü seçin"));
            return;
        }
        if (null != event.getFile()) {
            if (!transferFile(event.getFile(), newFilePath)) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "HATA", "Dosya yüklenirken hata oluştu"));
                return;
            }
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "BAŞARILI", "Dosya yükleme işi tamamlandı"));
        }
    }

    private boolean transferFile(UploadedFile file, String newFilePath) {
        OutputStream output;
        InputStream input;
        Path newPath;
        try {
            path = Paths.get(DIR, "AnaDepo", selectedFamily, selectdType, selectedBrand, selectedDocument);
            //path = Paths.get("D:", "Teknosa", selectedFamily, selectdType, selectedBrand, selectedDocument);
            input = file.getInputstream();
            if (null == newFilePath) {
                newPath = Files.createFile(Paths.get(path + FileSystems.getDefault().getSeparator() + dirsName + "-" + file.getFileName()));
            } else {
                String ex = file.getFileName().substring(file.getFileName().lastIndexOf("."));
                newPath = Files.createFile(Paths.get(path + FileSystems.getDefault().getSeparator() + dirsName + "-" + newFilePath + ex));
            }
            output = new FileOutputStream(newPath.toFile());
            int reader;
            byte[] bytes = new byte[(int) file.getSize()];
            while ((reader = input.read(bytes)) != -1) {
                output.write(bytes, 0, reader);
            }
            input.close();
            output.flush();
            output.close();
        } catch (FileNotFoundException ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "HATA", "Dosya Bulunamadı"));
            return false;
        } catch (IOException ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "HATA", "Dosya Transferinde bir sıkıntı oluştu."));
            return false;
        }
        return true;
    }
}
