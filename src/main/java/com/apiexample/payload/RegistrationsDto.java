package com.apiexample.payload;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class RegistrationsDto {
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    private Long id;

   @Size(min=2,message="Should be more than 2 characters")
    private String name;

   @Email(message="Invalid email format")
    private String email;

   @Size(min=10,max=10,message="Should be 10 digits")
    private String mobile;

    private  String message;



    public String getMessage() {
        return message;
    }


    public void setMessage(String message) {
        this.message = message;
    }
}