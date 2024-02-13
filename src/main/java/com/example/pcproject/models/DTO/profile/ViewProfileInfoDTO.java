package com.example.pcproject.models.DTO.profile;

import java.time.LocalDate;

public class ViewProfileInfoDTO {

    private Long id;

    private String username;

    private String firstName;

    private String lastName;

    private String organization;

    private String country;

    private String city;

    private String email;

    private String phoneNumber;

    private LocalDate createOn;

    private String userRole;

    private Long numberOfProducts;

    private Long numberOfSoldProduct;

    private Long allSoldAndCurrentProduct;

    public ViewProfileInfoDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

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

    public String getOrganization() {
        return organization;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public LocalDate getCreateOn() {
        return createOn;
    }

    public void setCreateOn(LocalDate createOn) {
        this.createOn = createOn;
    }

    public String getUserRole() {
        return userRole;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }

    public Long getNumberOfProducts() {
        return numberOfProducts;
    }

    public void setNumberOfProducts(Long numberOfProducts) {
        this.numberOfProducts = numberOfProducts;
    }

    public Long getNumberOfSoldProduct() {
        return numberOfSoldProduct;
    }

    public void setNumberOfSoldProduct(Long numberOfSoldProduct) {
        this.numberOfSoldProduct = numberOfSoldProduct;
    }

    public Long getAllSoldAndCurrentProduct() {
        return allSoldAndCurrentProduct;
    }

    public void setAllSoldAndCurrentProduct(Long allSoldAndCurrentProduct) {
        this.allSoldAndCurrentProduct = allSoldAndCurrentProduct;
    }
}
