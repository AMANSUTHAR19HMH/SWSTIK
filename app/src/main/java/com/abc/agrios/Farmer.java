package com.abc.agrios;

public class Farmer {
    private String username;
    private String password;
    private String email;
    private String mobileNo;
    private String name;
    private String pincode;
    private String userRole;
    private Address address;

    public Farmer(String username, String password, String email, String mobileNo,
                  String name, String pincode, String userRole, Address address) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.mobileNo = mobileNo;
        this.name = name;
        this.pincode = pincode;
        this.userRole = userRole;
        this.address = address;
    }
}
