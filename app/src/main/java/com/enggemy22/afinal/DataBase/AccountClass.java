package com.enggemy22.afinal.DataBase;

public class AccountClass {
    private String fullName;
    private String userName;
    private String password;
    private long number;
    private String gender;

    public AccountClass() {
    }

    public AccountClass(String fullName, String userName, String password, long number, String gender) {
        this.fullName = fullName;
        this.userName = userName;
        this.password = password;
        this.number = number;
        this.gender = gender;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public long getNumber() {
        return number;
    }

    public void setNumber(long number) {
        this.number = number;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}

