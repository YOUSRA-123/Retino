package com.example.retinolab;

public class AideNew {

    private String nameEnf;
    private String nameTut;
    private String email;
    private String phone;
    private String msg;

    public String getNameEnf() {
        return nameEnf;
    }

    public void setNameEnf(String nameEnf) {
        this.nameEnf = nameEnf;
    }

    public String getNameTut() {
        return nameTut;
    }

    public void setNameTut(String nameTut) {
        this.nameTut = nameTut;
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

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public AideNew(String nameEnf, String nameTut, String email, String phone, String msg) {
        this.nameEnf = nameEnf;
        this.nameTut = nameTut;
        this.email = email;
        this.phone = phone;
        this.msg = msg;
    }

    public AideNew() {}
}
