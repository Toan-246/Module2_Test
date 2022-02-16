package com.codegym.model;

import java.io.Serializable;

public class Contact implements Serializable {
    private String name;
    private String phoneNum;
    private String adress;
    private String mail;

    public Contact() {
    }

    public Contact(String name, String phoneNum, String adress, String mail) {
        this.name = name;
        this.phoneNum = phoneNum;
        this.adress = adress;
        this.mail = mail;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    @Override
    public String toString() {
        return "Tên: " + name + ", SĐT: " +  phoneNum+", Địa Chỉ: " + adress + ", Email: " + mail;
    }
}
