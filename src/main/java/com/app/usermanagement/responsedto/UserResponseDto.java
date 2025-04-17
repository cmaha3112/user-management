package com.app.usermanagement.responsedto;

import com.app.usermanagement.model.User;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;


public class UserResponseDto {


    private String userId;
    private String name;
    private String password;
    private String email;

    private LocalDateTime createDate;

    private LocalDateTime updatedDate;

    private boolean active = true;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public LocalDateTime getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
    }

    public LocalDateTime getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(LocalDateTime updatedDate) {
        this.updatedDate = updatedDate;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
    public UserResponseDto(User user) {
        this.userId = user.getUserId();
        this.name = user.getName();
        this.password = user.getPassword(); // or "****" to mask
        this.email = user.getEmail();
        this.createDate = user.getCreateDate();
        this.updatedDate = user.getUpdatedDate();
        this.active = user.isActive();
    }
}
