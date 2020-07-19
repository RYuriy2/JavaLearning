package ru.qa.learn.addressbook.model;

public class UserData {
    private final String id;
    private final String firstname;
    private final String address;
    private final String lastname;
    private final String phoneNumber;
    private final String email;
    private final String group;

    public UserData(String id, String firstname, String address, String lastname, String phoneNumber, String email, String group) {
        this.id = id;
        this.firstname = firstname;
        this.address = address;
        this.lastname = lastname;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.group = group;
    }
    public UserData(String firstname, String address, String lastname, String phoneNumber, String email, String group) {
        this.id = null;
        this.firstname = firstname;
        this.address = address;
        this.lastname = lastname;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.group = group;
    }

    public String getID() {
        return id;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getAddress() {
        return address;
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

    public String getGroup() { return group; }

    @Override
    public String toString() {
        return "UserData{" +
                "id='" + id + '\'' +
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

        if (id != null ? !id.equals(userData.id) : userData.id != null) return false;
        if (firstname != null ? !firstname.equals(userData.firstname) : userData.firstname != null) return false;
        if (address != null ? !address.equals(userData.address) : userData.address != null) return false;
        if (lastname != null ? !lastname.equals(userData.lastname) : userData.lastname != null) return false;
        if (phoneNumber != null ? !phoneNumber.equals(userData.phoneNumber) : userData.phoneNumber != null)
            return false;
        return email != null ? email.equals(userData.email) : userData.email == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (firstname != null ? firstname.hashCode() : 0);
        result = 31 * result + (address != null ? address.hashCode() : 0);
        result = 31 * result + (lastname != null ? lastname.hashCode() : 0);
        result = 31 * result + (phoneNumber != null ? phoneNumber.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        return result;
    }
}
