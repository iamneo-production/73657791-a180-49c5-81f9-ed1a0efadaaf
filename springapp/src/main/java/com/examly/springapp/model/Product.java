package com.examly.springapp.model;

import java.io.Serializable;
import java.sql.Time;
import java.text.ParseException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
//import java.text.SimpleDateFormat;
//import java.sql.Date;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.List;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

import org.hibernate.annotations.Parameter;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.examly.springapp.model.User;

@Entity
@Table(name = "products")
public class Product implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    @NotBlank
    private String productName;
    @Column
    @NotBlank
    private String productModelNo;
    @Column(nullable = false)
    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date dateOfPurchase;

    @Column
    @NotBlank
    @Size(min = 8, max = 10)
    private String contactNumber;
    @Column
    @Size(max = 120, min = 10)
    private String problemDescription;
    @Column(nullable = false)
    // @NotBlank
    @JsonFormat(pattern = "HH:mm")
    private LocalTime availableSlots;
    // @ManyToOne(targetEntity = User.class, cascade = CascadeType.ALL)
    @ManyToOne(targetEntity = User.class, fetch = FetchType.LAZY)
    // prod_id-PRI
    // user_id-FK
    //@JoinTable(name = "cus_prod", joinColumns = @JoinColumn(name = "prod_id"), inverseJoinColumns = @JoinColumn(name = "user_id"))
    @JoinTable(name = "cus_prod", joinColumns = @JoinColumn(name = "prod_id"),
     inverseJoinColumns = {@JoinColumn(name = "user_id",referencedColumnName = "id"),@JoinColumn(name="user_name",referencedColumnName = "username")})
    
    // @JoinColumn(name = "user_id", referencedColumnName = "id")
    // @OnDelete(action = OnDeleteAction.CASCADE)
    // @JsonIgnore
    // @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private User userperson;

    // private Time availableSlots;

    // private Time availableSlots;

    // public Product(String productName, String productModelNo, Date
    // dateOfPurchase, String contactNumber, String problemDescription, Time
    // availableSlots) {
    // this.productName = productName;
    // this.productModelNo = productModelNo;
    // this.dateOfPurchase = dateOfPurchase;
    // this.contactNumber = contactNumber;
    // this.problemDescription = problemDescription;
    // this.availableSlots = availableSlots;
    // }
    // public Product(String productName, String productModelNo, Date
    // dateOfPurchase, String contactNumber, String problemDescription) throws
    // ParseException {
    // this.productName = productName;
    // this.productModelNo = productModelNo;
    // this.dateOfPurchase = dateOfPurchase;
    // //this.dateOfPurchase = new SimpleDateFormat("dd/MM/yyyy").parse(strdate);
    // this.contactNumber = contactNumber;
    // this.problemDescription = problemDescription;
    // }
    public Product(String productName, String productModelNo, Date dateOfPurchase, String contactNumber,
            String problemDescription, String strTime) {
        this.productName = productName;
        this.productModelNo = productModelNo;
        this.dateOfPurchase = dateOfPurchase;
        this.contactNumber = contactNumber;
        this.problemDescription = problemDescription;
        // this.availableSlots = availableSlots;
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm");
        LocalTime lt = LocalTime.parse(strTime, dtf);
        this.availableSlots = lt;
    }

    public Product() {
    }

    public String getProductName() {
        return this.productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductModelNo() {
        return this.productModelNo;
    }

    public void setProductModelNo(String productModelNo) {
        this.productModelNo = productModelNo;
    }

    public Date getDateOfPurchase() {
        return this.dateOfPurchase;
    }

    public void setDateOfPurchase(Date dateOfPurchase) {
        this.dateOfPurchase = dateOfPurchase;
    }
    // public String getDateOfPurchase() {
    // return this.dateOfPurchase;
    // }

    // public void setDateOfPurchase(String dateOfPurchase) {
    // this.dateOfPurchase = dateOfPurchase;
    // }

    public String getContactNumber() {
        return this.contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getProblemDescription() {
        return this.problemDescription;
    }

    public void setProblemDescription(String problemDescription) {
        this.problemDescription = problemDescription;
    }

    // public Time getAvailableSlots() {
    // return this.availableSlots;
    // }

    // public void setAvailableSlots(Time availableSlots) {
    // this.availableSlots = availableSlots;
    // }
    public LocalTime getAvailableSlots() {
        return this.availableSlots;
    }

    public void setAvailableSlots(LocalTime availableSlots) {
        this.availableSlots = availableSlots;
    }

    public User getUserperson() {
        return this.userperson;
    }

    public void setUserperson(User userperson) {
        this.userperson = userperson;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
