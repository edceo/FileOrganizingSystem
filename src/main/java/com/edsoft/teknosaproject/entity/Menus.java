/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.edsoft.teknosaproject.entity;

import java.io.Serializable;
import javax.faces.model.SelectItem;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Yusuf ÖNDER
 */
@Entity
@Table(catalog = "", schema = "APP")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Menus.findAll", query = "SELECT m FROM Menus m"),
    @NamedQuery(name = "Menus.findByValued", query = "SELECT m FROM Menus m WHERE m.valued = :valued"),
    @NamedQuery(name = "Menus.findByLabel", query = "SELECT m FROM Menus m WHERE m.label = :label")})
public class Menus extends SelectItem implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 8)
    @Column(nullable = false, length = 8)
    private String valued;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 32)
    @Column(nullable = false, length = 32)
    private String label;

    public Menus() {
    }

    public Menus(String valued) {
        this.valued = valued;
    }

    public Menus(String valued, String label) {
        super(valued, label);
        this.valued = valued;
        this.label = label;
    }

    public String getValued() {
        return valued;
    }

    public void setValued(String valued) {
        this.valued = valued;
    }

    public String getLabel1() {
        return label;
    }

    public void setLabel1(String label) {
        this.label = label;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (valued != null ? valued.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Menus)) {
            return false;
        }
        Menus other = (Menus) object;
        if ((this.valued == null && other.valued != null) || (this.valued != null && !this.valued.equals(other.valued))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.edsoft.teknosaproject.entity.Menus[ valued=" + valued + " ]";
    }
    
}
