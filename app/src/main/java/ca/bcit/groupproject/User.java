package ca.bcit.groupproject;

public class User {
    public String fullName, postalCode, email;

    public User() {
    }

    public User(String fullName, String postalCode, String email) {
        this.fullName = fullName;
        this.postalCode = postalCode;
        this.email = email;
    }



    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
