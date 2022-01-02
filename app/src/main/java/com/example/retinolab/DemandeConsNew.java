package com.example.retinolab;

public class DemandeConsNew {



        private String name;
        private String date;
        private String email;
        private String phone;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public DemandeConsNew(String name, String date, String email, String phone) {
        this.name = name;
        this.date = date;
        this.email = email;
        this.phone = phone;
    }

    public DemandeConsNew() {}




}
