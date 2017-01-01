package org.techstuff.auth.data;

public class User {
    private String userName;
    private String firstName;
    private String lastName;

    public void setUserName(String name) {
        this.userName = name;
    }

    public String getUserName() {
        return userName;
    }

    public void setFirstName(String name) {
        firstName = name;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setLastName(String name) {
        lastName = name;
    }

    public String getLastName() {
        return lastName;
    }
}
