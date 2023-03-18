package com.examly.springapp.model.child;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.examly.springapp.model.Product;
import com.examly.springapp.model.ServiceCenter;
@Entity
@Table(name="plastic_products")
public class PlasticProduct extends Product{
    private String plastictype;

    public String getPlastictype() {
        return plastictype;
    }

    public void setPlastictype(String plastictype) {
        this.plastictype = plastictype;
    }

    public PlasticProduct(String productName, String productModelNo, Date dateOfPurchase, String contactNumber,
            String problemDescription, String strTime, ServiceCenter serviceCenter, String plastictype) {
        super(productName, productModelNo, dateOfPurchase, contactNumber, problemDescription, strTime, serviceCenter);
        this.plastictype = plastictype;
    }

    public PlasticProduct(String productName, String productModelNo, Date dateOfPurchase, String contactNumber,
            String problemDescription, String strTime, String plastictype) {
        super(productName, productModelNo, dateOfPurchase, contactNumber, problemDescription, strTime);
        this.plastictype = plastictype;
    }
    
    public PlasticProduct(String productName, String productModelNo, Date dateOfPurchase, String contactNumber,
            String problemDescription, String strTime, ServiceCenter serviceCenter) {
        super(productName, productModelNo, dateOfPurchase, contactNumber, problemDescription, strTime, serviceCenter);
    }

    public PlasticProduct(){
        
    }
}
