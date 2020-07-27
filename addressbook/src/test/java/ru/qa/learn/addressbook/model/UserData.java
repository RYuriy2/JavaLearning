package ru.qa.learn.addressbook.model;

public class UserData {
    private int id = Integer.MAX_VALUE;
    private String firstname;
    private String address;
    private String lastname;
    private String phoneNumber;
    private String email;
    private String group;


//    public UserData(int id, String firstname, String address, String lastname, String phoneNumber, String email, String group) {
//        this.id = id;
//        this.firstname = firstname;
//        this.address = address;
//        this.lastname = lastname;
//        this.phoneNumber = phoneNumber;
//        this.email = email;
//        this.group = group;
//    }
//    public UserData(String firstname, String address, String lastname, String phoneNumber, String email, String group) {
//        this.id = Integer.MAX_VALUE;
//        this.firstname = firstname;
//        this.address = address;
//        this.lastname = lastname;
//        this.phoneNumber = phoneNumber;
//        this.email = email;
//        this.group = group;
//    }

    public int getID() {
        return id;
    }

    public UserData withID(int id) {
        this.id = id;
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

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public UserData withPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public UserData withEmail(String email) {
        this.email = email;
        return this;
    }

    public String getGroup() { return group; }

    public UserData withGroup(String group) {
        this.group = group;
        return this;
    }

    @Override
    public String toString() {
        return "UserData{" +
                "id=" + id +
                ", firstname='" + firstname + '\'' +
                ", address='" + address + '\'' +
                ", lastname='" + lastname + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserData userData = (UserData) o;

        if (firstname != null ? !firstname.equals(userData.firstname) : userData.firstname != null) return false;
        return lastname != null ? lastname.equals(userData.lastname) : userData.lastname == null;
    }

    @Override
    public int hashCode() {
        int result = firstname != null ? firstname.hashCode() : 0;
        result = 31 * result + (lastname != null ? lastname.hashCode() : 0);
        return result;
    }
}
