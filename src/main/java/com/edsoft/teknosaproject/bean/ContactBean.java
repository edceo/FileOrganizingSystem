/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.edsoft.teknosaproject.bean;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author Yusuf ÖNDER
 */
@ManagedBean(name = "contact")
@ViewScoped
public class ContactBean {
    private String edsoft;
    private String yusuf;
    private String thankyou;
    
    @PostConstruct
    public void init() {
        edsoft = "EDSOFT, günümüz yazılım şirketlerinden farklı olarak şirketlere teknolojik çözümler üretmekle beraber gençleri de teknolojik eğitimlere"
                + " alarak onlarında gelişmesine katkıda bulunan bir yazılım şirketidir. Amacı, ülke genelindeki gençleri hedef alıp onları çağın teknolojilerine "
                + "-üretim açısından- ayak uydurmaya çalışmaktır. EDSOFT, ÜTOPİK BİR DÜNYANIN GERÇEKLENEN İLK SOMUT PARÇASIDIR.";
        yusuf = "EDSOFT'un kurucu başkanıdır. Yıldız Teknik Üniversitesi'nde Bilgisayar Mühendisliği okumaktadır. Gayesi EDSOFT'u gerçekleştirmek ve onu "
                + "herkese tanıtmak ve ispatlamaktır. ";
        thankyou = "Öncelikle bu projeyi almama vesile olan bana destek olan ve güvenini bir an olsun eksiltmeyen Mimar Aslan Hocam'a sonsuz saygı ve sevgilerimi sunuyorum. Bu sunduğu fırsat ile kendimi geliştirmekten "
                + "öte şeyler kazandığımı belirtmek isterim. Allah razı olsun hocam :)  \n Bununla beraber Serdar Bey "
                + "ve Turgay Bey'e de bana karşı göstermiş oldukları hoşgörülü ve sabırlı tutumlarıyla beraber bana duydukları güvenden dolayı teşekkürlerimi borç bilirim.  ";
    }

    public String getEdsoft() {
        return edsoft;
    }

    public String getThankyou() {
        return thankyou;
    }

    public String getYusuf() {
        return yusuf;
    }
}
