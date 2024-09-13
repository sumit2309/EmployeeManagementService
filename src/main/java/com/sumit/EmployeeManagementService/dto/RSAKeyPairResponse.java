package com.sumit.EmployeeManagementService.dto;

public class RSAKeyPairResponse {

    private Long id;
    private String publicKeyBase64;

    // Constructors
    public RSAKeyPairResponse() {}

    public RSAKeyPairResponse(Long id, String publicKeyBase64) {
        this.id = id;
        this.publicKeyBase64 = publicKeyBase64;
    }

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPublicKeyBase64() {
        return publicKeyBase64;
    }

    public void setPublicKeyBase64(String publicKeyBase64) {
        this.publicKeyBase64 = publicKeyBase64;
    }
}

