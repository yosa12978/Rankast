package com.yosa.rankast.dtos;

public class TokenDto {
    private Long id;
    private String username;
    private String token;
    private String fullName;

    public TokenDto() {
    }

    public TokenDto(Long id, String username, String token, String fullName) {
        this.id = id;
        this.username = username;
        this.token = token;
        this.fullName = fullName;
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

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
}
