package com.abc.agrios.apirequest;

public class SignupRequest {
    private String username;
    private String password;
    private String email;
    private String mobileNo;
    private String name;
    private String pincode;
    private String userRole;
    private Address address;

    // Constructor, Getters, and Setters
    public SignupRequest(String username, String password, String email, String mobileNo, String name, String pincode, String userRole, Address address) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.mobileNo = mobileNo;
        this.name = name;
        this.pincode = pincode;
        this.userRole = userRole;
        this.address = address;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPincode() {
        return pincode;
    }

    public void setPincode(String pincode) {
        this.pincode = pincode;
    }

    public String getUserRole() {
        return userRole;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    // Nested Address Class
    public static class Address {
        private String street;
        private String city;
        private String state;
        private String country;
        private String zipCode;

        public Address(String street, String city, String state, String country, String zipCode) {
            this.street = street;
            this.city = city;
            this.state = state;
            this.country = country;
            this.zipCode = zipCode;
        }


    }
}
