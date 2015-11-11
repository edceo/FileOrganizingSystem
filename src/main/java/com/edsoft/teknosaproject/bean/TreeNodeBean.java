/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.edsoft.teknosaproject.bean;

import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.TrueFileFilter;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;

/**
 *
 * @author Yusuf ÖNDER
 */
@ManagedBean(name = "tree")
@ViewScoped
public class TreeNodeBean implements Serializable {

    private TreeNode root;
    private String target;
    private LinkedList<File> fileList;
    private final String DIR = "D:";

    @PostConstruct
    public void initialize() {
        root = null;
        fileList = null;
        //fileList = (LinkedList<File>) FileUtils.listFilesAndDirs(Paths.get("E:", "T_K_B AnaDepo").toFile(), TrueFileFilter.INSTANCE, TrueFileFilter.INSTANCE);
        fileList = (LinkedList<File>) FileUtils.listFilesAndDirs(Paths.get(DIR, "AnaDepo").toFile(), TrueFileFilter.INSTANCE, TrueFileFilter.INSTANCE);
        root = new DefaultTreeNode(fileList.getFirst().getName(), null);
        rec(root.getChildren(), fileList, 1, fileList.getFirst(), root);
    }

    public TreeNode getRoot() {
        return root;
    }

    public void setRoot(TreeNode root) {
        this.root = root;
    }

    public LinkedList<File> getFileList() {
        return fileList;
    }

    public void setFileList(LinkedList<File> fileList) {
        this.fileList = fileList;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    private void rec(List<TreeNode> children, LinkedList<File> fileList, int i, File parent, TreeNode root) {
        if (i == fileList.size()) {
            return;
        }
        File file = fileList.get(i);
        TreeNode node;
        if (file.getParentFile().equals(parent)) {
            node = new DefaultTreeNode(file.getName());
            children.add(node);
            if (!fileList.get(i).isFile()) {
                i++;
                rec(node.getChildren(), fileList, i, file, node);
            } else {
                i++;
                rec(node.getParent().getChildren(), fileList, i, file.getParentFile(), node.getParent());
            }
        } else {
            node = root;
            file = parent;
            rec(node.getParent().getChildren(), fileList, i, file.getParentFile(), node.getParent());
        }
    }

    public void saveFile() {
        Path path = Paths.get("E:", "TREE", "hiyerarşi.txt");
        //Path path = Paths.get("E:", "Hiyerarşi", "hiyerarşi.txt");
        OutputStream stream;
        ObjectOutput object;
        try {
            stream = new FileOutputStream(path.toFile());
            object = new ObjectOutputStream(stream);
            object.writeObject(root);
            object.flush();
            object.close();
            stream.close();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "TAMMA", "Başarılı"));
        } catch (IOException ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "HATA", "Kayıt sırasında hata oldu"));
        }
    }

    /*public void opn() {
        try {
            Desktop.getDesktop().open(Paths.get("D:", "AnaDepo", target).toFile());
        } catch (IOException | IllegalArgumentException ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Bulunamadı", "Lütfen görününen 5 klasörden birini seçiniz"));
        }
    }*/
}
