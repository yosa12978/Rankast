package com.yosa.rankast.dtos;

public class LoginDto {
    public String username;
    public String password;

    public LoginDto() {
    }

    public LoginDto(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
