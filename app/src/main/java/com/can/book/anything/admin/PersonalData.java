package com.can.book.anything.admin;

import com.google.firebase.firestore.Exclude;

public class PersonalData {

    public  PersonalData(){
        /* no needed */
    }

    public PersonalData(String name, String phoneNumber, String address, String serviceArea) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.serviceArea = serviceArea;
    }

    @Exclude
    public String getDocumentId() {
        return documentId;
    }

    public void setDocumentId(String documentId) {
        this.documentId = documentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getServiceArea() {
        return serviceArea;
    }

    public void setServiceArea(String serviceArea) {
        this.serviceArea = serviceArea;
    }

    private String name;
    private String phoneNumber;
    private String address;
    private String serviceArea;
    private String documentId;
}
