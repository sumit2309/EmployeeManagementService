package com.sumit.EmployeeManagementService.entity;


import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String password; // Store password securely in a real-world scenario
    private LocalDateTime loginTime;
    private LocalDateTime logoutTime;

    private Long rsaKeyId; // Store RSA key ID

    // Constructors, getters, and setters

    public Employee() {}

    public Employee(String username, String password, Long rsaKeyId) {
        this.username = username;
        this.password = password;
        this.rsaKeyId = rsaKeyId;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDateTime getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(LocalDateTime loginTime) {
        this.loginTime = loginTime;
    }

    public LocalDateTime getLogoutTime() {
        return logoutTime;
    }

    public void setLogoutTime(LocalDateTime logoutTime) {
        this.logoutTime = logoutTime;
    }

    public Long getRsaKeyId() {
        return rsaKeyId;
    }

    public void setRsaKeyId(Long rsaKeyId) {
        this.rsaKeyId = rsaKeyId;
    }
}


