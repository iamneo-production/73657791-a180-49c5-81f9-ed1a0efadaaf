package com.examly.springapp.model.child;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.examly.springapp.model.Product;
import com.examly.springapp.model.ServiceCenter;
@Entity
@Table(name = "wooden_products")
public class WoodProduct extends Product{
    @Column(name="wood_type")
    private String woodtype;

    public String getWoodtype() {
        return woodtype;
    }

    public void setWoodtype(String woodtype) {
        this.woodtype = woodtype;
    }

    public WoodProduct(String productName, String productModelNo, Date dateOfPurchase, String contactNumber,
            String problemDescription, String strTime, ServiceCenter serviceCenter, String woodtype) {
        super(productName, productModelNo, dateOfPurchase, contactNumber, problemDescription, strTime, serviceCenter);
        this.woodtype = woodtype;
    }

    public WoodProduct(String productName, String productModelNo, Date dateOfPurchase, String contactNumber,
            String problemDescription, String strTime, String woodtype) {
        super(productName, productModelNo, dateOfPurchase, contactNumber, problemDescription, strTime);
        this.woodtype = woodtype;
    }

    public WoodProduct(String productName, String productModelNo, Date dateOfPurchase, String contactNumber,
            String problemDescription, String strTime, ServiceCenter serviceCenter) {
        super(productName, productModelNo, dateOfPurchase, contactNumber, problemDescription, strTime, serviceCenter);
    }

    public WoodProduct() {
    }
    
    
    
}
