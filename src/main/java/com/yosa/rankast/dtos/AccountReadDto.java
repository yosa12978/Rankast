package com.yosa.rankast.dtos;

public class AccountReadDto {
    public String username;
    public String password;
    public String email;
    public String fullName;

    public AccountReadDto() {
    }

    public AccountReadDto(String username, String password, String email, String fullName) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.fullName = fullName;
    }
}
