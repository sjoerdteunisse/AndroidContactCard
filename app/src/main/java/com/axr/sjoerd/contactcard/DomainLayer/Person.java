package com.axr.sjoerd.contactcard.DomainLayer;

import java.io.Serializable;

public class Person implements Serializable {

    //Reflection to {0},{1}
    private String firstName;
    private String lastName;
    private String title;
    private String gender;
    private String email;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getProfileThumbnail() {
        return profileThumbnail;
    }

    public void setProfileThumbnail(String profileThumbnail) {
        this.profileThumbnail = profileThumbnail;
    }

    private String city;
    private String profileThumbnail;


    public Person(String firstName, String lastName, String title, String gender, String email, String city, String profileThumbnail) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.title = title;
        this.gender = gender;
        this.email = email;
        this.city = city;
        this.profileThumbnail = profileThumbnail;
    }

}
