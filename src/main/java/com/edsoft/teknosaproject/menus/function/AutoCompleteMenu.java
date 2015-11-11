/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.edsoft.teknosaproject.menus.function;

import java.util.List;
import org.primefaces.event.SelectEvent;

/**
 *
 * @author Yusuf ÖNDER
 */
public interface AutoCompleteMenu {
    
    public List<String> autoFamily(String query);

    public void selectFamily(SelectEvent event);

    public List<String> auto1Types(String query);

    public void selectTypes(SelectEvent event);

    public List<String> autoBrand(String query);

    public void selectBrand(SelectEvent event);

    public List<String> autoDocument(String query);

    public void selectDocument(SelectEvent event);
    
}
