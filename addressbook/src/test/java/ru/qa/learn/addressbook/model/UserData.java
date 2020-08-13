package ru.qa.learn.addressbook.model;

import com.google.gson.annotations.Expose;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamOmitField;
import org.hibernate.annotations.Type;
import javax.persistence.*;
import java.io.File;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@XStreamAlias("user")
@Entity
@Table(name = "addressbook")
public class UserData {
    @XStreamOmitField
    @Id
    @Column(name = "id")
    private int id = Integer.MAX_VALUE;
    @Expose
    @Column(name = "firstname")
    private String firstname;
    @Expose
    @Column(name = "address")
    @Type(type = "text")
    private String address;
    @Expose
    @Column(name = "lastname")
    private String lastname;
    @Expose
    @Column(name = "home")
    @Type(type = "text")
    private String homePhone;
    @Expose
    @Column(name = "mobile")
    @Type(type = "text")
    private String mobilePhone;
    @Expose
    @Column(name = "work")
    @Type(type = "text")
    private String workPhone;
    @Transient
    private String allPhones;
    @Expose
    @Column(name = "email")
    @Type(type = "text")
    private String email1;
    @Expose
    @Column(name = "email2")
    @Type(type = "text")
    private String email2;
    @Expose
    @Column(name = "email3")
    @Type(type = "text")
    private String email3;
    @Transient
    private String allEmail;
    @Column(name = "photo")
    @Type(type = "text")
    private String photo;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "address_in_groups",
            joinColumns = @JoinColumn(name = "id"),
            inverseJoinColumns = @JoinColumn(name = "group_id"))
    private Set<GroupData> groups = new HashSet<GroupData>();

    public Groups getGroups() {
        return new Groups(groups);
    }

    public UserData removeGroups(GroupData group) {
        groups.remove(group);
        return this;
    }

    public int getID() {
        return id;
    }

    public UserData withID(int id) {
        this.id = id;
        return this;
    }

    public File getPhoto() {
        return new File(photo);
    }

    public UserData withPhoto(File photo) {
        this.photo = photo.getPath();
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserData userData = (UserData) o;
        return id == userData.id &&
                Objects.equals(firstname, userData.firstname) &&
                Objects.equals(address, userData.address) &&
                Objects.equals(lastname, userData.lastname) &&
                Objects.equals(homePhone, userData.homePhone) &&
                Objects.equals(email1, userData.email1);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstname, address, lastname, homePhone, email1);
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

    public UserData inGroup(GroupData group) {
        groups.add(group);
        return this;
    }
}
