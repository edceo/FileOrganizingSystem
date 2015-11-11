/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.edsoft.teknosaproject.bean;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author Yusuf ÖNDER
 */
@ManagedBean(name = "rapor2")
@ViewScoped
public class ReportBeanTwo implements Serializable {

    private List<File> fileList = ConnectBean.list;
    private List<File> filtered;
    private List<File> deletedFile;

    @PostConstruct
    public void init() {
        filtered = new ArrayList<>();
        deletedFile = new ArrayList<>();
    }

    public List<File> getFileList() {
        return fileList;
    }

    public void setFileList(List<File> fileList) {
        this.fileList = fileList;
    }

    public List<File> getFiltered() {
        return filtered;
    }

    public void setFiltered(List<File> filtered) {
        this.filtered = filtered;
    }

    public List<File> getDeletedFile() {
        return deletedFile;
    }

    public void setDeletedFile(List<File> deletedFile) {
        this.deletedFile = deletedFile;
    }
    
    public void delete() {
        for(File file : deletedFile) {
            file.delete();
        }
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Başarılı", "Silindi"));
    }
    
    public int size() {
        return filtered.size();
    }
    
    public void allSelect() {
        deletedFile.clear();
        for(File file : filtered) {
            deletedFile.add(file);
        }
    }
}
