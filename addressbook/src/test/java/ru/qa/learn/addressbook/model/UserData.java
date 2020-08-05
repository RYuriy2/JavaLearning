package ru.qa.learn.addressbook.model;

import com.google.gson.annotations.Expose;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamOmitField;

import java.io.File;
import java.util.Objects;

@XStreamAlias("user")
public class UserData {
    @XStreamOmitField
    private int id = Integer.MAX_VALUE;
    @Expose
    private String firstname;
    @Expose
    private String address;
    @Expose
    private String lastname;
    @Expose
    private String homePhone;
    @Expose
    private String mobilePhone;
    @Expose
    private String workPhone;
    private String allPhones;
    @Expose
    private String email1;
    @Expose
    private String email2;
    @Expose
    private String email3;
    @Expose
    private String allEmail;
    private String group;
    private File photo;

    public int getID() {
        return id;
    }

    public UserData withID(int id) {
        this.id = id;
        return this;
    }

    public File getPhoto() {
        return photo;
    }

    public UserData withPhoto(File photo) {
        this.photo = photo;
        return this;
    }

    public String getFirstname() {
        return firstname;
    }

    public UserData withFirstname(String firstname) {
        this.firstname = firstname;
        return this;
    }

    public String getAddress() {
        return address;
    }

    public UserData withAddress(String address) {
        this.address = address;
        return this;
    }

    public String getLastname() {
        return lastname;
    }

    public UserData withLastname(String lastname) {
        this.lastname = lastname;
        return this;
    }

    public String getHomePhone() {
        return homePhone;
    }

    public UserData withHomePhoneNumber(String phoneNumber) {
        this.homePhone = phoneNumber;
        return this;
    }

    public String getAllPhone() {
        return allPhones;
    }

    public UserData withAllPhoneNumber(String allPhones) {
        this.allPhones = allPhones;
        return this;
    }

    public String getWorkPhone() {
        return workPhone;
    }

    public UserData withWorkPhoneNumber(String workPhone) {
        this.workPhone = workPhone;
        return this;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public UserData withMobilePhoneNumber(String mobilePhone) {
        this.mobilePhone = mobilePhone;
        return this;
    }

    public String getEmail1() {
        return email1;
    }

    public UserData withEmail1(String email) {
        this.email1 = email;
        return this;
    }

    public String getEmail2() {
        return email2;
    }

    public UserData withEmail2(String email) {
        this.email2 = email;
        return this;
    }

    public String getEmail3() {
        return email3;
    }

    public UserData withEmail3(String email) {
        this.email3 = email;
        return this;
    }

    public String getAllEmail() {
        return allEmail;
    }

    public UserData withAllEmail(String email) {
        this.allEmail = email;
        return this;
    }

    public String getGroup() { return group; }

    public UserData withGroup(String group) {
        this.group = group;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserData userData = (UserData) o;
        return id == userData.id &&
                Objects.equals(firstname, userData.firstname) &&
                Objects.equals(lastname, userData.lastname);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstname, lastname);
    }

    @Override
    public String toString() {
        return "UserData{" +
                "id=" + id +
                ", firstname='" + firstname + '\'' +
                ", address='" + address + '\'' +
                ", lastname='" + lastname + '\'' +
                ", homePhone='" + homePhone + '\'' +
                ", mobilePhone='" + mobilePhone + '\'' +
                ", workPhone='" + workPhone + '\'' +
                ", allPhones='" + allPhones + '\'' +
                ", email1='" + email1 + '\'' +
                ", email2='" + email2 + '\'' +
                ", email3='" + email3 + '\'' +
                ", allEmail='" + allEmail + '\'' +
                '}';
    }

}
