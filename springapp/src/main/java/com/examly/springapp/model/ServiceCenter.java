package com.examly.springapp.model;

import org.hibernate.annotations.GenericGenerator;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import org.hibernate.annotations.Parameter;


@Entity
@Table(name = "serviceCenter")
public class ServiceCenter implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    //@Column(name="serv_id")
    private Long sid;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    @Digits(message = "Number should contain 10 digits.", fraction = 0, integer = 10)
    private String phone;
    @Column(nullable = false)
    private String address;
    @Column(nullable = false)
    private String img_url;
    @NotBlank
    @Column(nullable = false, length = 50)
    @Email(message = "Email should be valid")
    private String mailId;
    @Column(nullable = false, length = 120)
    private String description;
    public ServiceCenter(){}
    public ServiceCenter(String name, String phone, String address, String img_url, String mailId, String description) {
        this.name = name;
        this.phone = phone;
        this.address = address;
        this.img_url = img_url;
        this.mailId = mailId;
        this.description = description;
    }

    public Long getSid() {
        return this.sid;
    }

    public void setSid(Long sid) {
        this.sid = sid;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return this.phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getImg_url() {
        return this.img_url;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }

    public String getMailId() {
        return this.mailId;
    }

    public void setMailId(String mailId) {
        this.mailId = mailId;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
