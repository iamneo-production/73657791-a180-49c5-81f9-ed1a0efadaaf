package com.examly.springapp.payload.request;


import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class AppoDetails {
    private Long id;

    @NotBlank
    private String productName;

    @NotBlank
    private String productModelNo;

    //@NotBlank
    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date dateOfPurchase;

    @NotBlank
    @Size(max = 12)
    private String contactNumber;

    @NotBlank
    @Size(max = 12)
    private String problemDescription;

    @NotBlank
    @JsonFormat(pattern = "HH:mm")
    private LocalTime availableSlots;

    public AppoDetails() {
    }

    public AppoDetails(String productName, String productModelNo, Date dateOfPurchase, String contactNumber,
            String problemDescription, String strTime) {

        this.productName = productName;
        this.productModelNo = productModelNo;
        this.dateOfPurchase = dateOfPurchase;
        this.contactNumber = contactNumber;
        this.problemDescription = problemDescription;
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm");
        LocalTime lt = LocalTime.parse(strTime, dtf);
        this.availableSlots = lt;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public LocalTime getAvailableSlots() {
        return this.availableSlots;
    }

    public void setAvailableSlots(LocalTime availableSlots) {
        this.availableSlots = availableSlots;
    }

}
