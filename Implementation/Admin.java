/*
* File Name: BorderDecorator.java
* Assignment: Final Project
* Lab section: B02
* Completed by: Adam Yuen
* Development Date: November 19, 2024
*/
package Implementation;

public class Admin {
    private String name;
    private String email;
    private String password;

    public String getName() {
        return this.name;
    }

    public String getEmail() {
        return this.email;
    }

    public String getPassword() {
        return this.password;
    }

    public void setName(String desiredName) {
        this.name = desiredName;
    }

    public void setEmail(String desiredEmail) {
        this.email = desiredEmail;
    }

    public void setPassword(String desiredPassword) {
        this.password = desiredPassword;
    }

    Admin() {
        this.name = "Default";
        this.email = "Default";
        this.password = "Default";
    }
}

