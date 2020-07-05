package ru.qa.learn.addressbook.model;

public class UserData {
    private final String firstname;
    private final String midlname;
    private final String lastname;
    private final String phoneNumber;
    private final String email;

    public UserData(String firstname, String midlname, String lastname, String phoneNumber, String email) {
        this.firstname = firstname;
        this.midlname = midlname;
        this.lastname = lastname;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getMidlname() {
        return midlname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmail() {
        return email;
    }
}
