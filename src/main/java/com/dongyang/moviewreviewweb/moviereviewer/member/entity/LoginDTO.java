package com.dongyang.moviewreviewweb.moviereviewer.member.entity;

public class LoginDTO {
    private String id;
    private String pw;
    public LoginDTO (String id, String pw) {
        this.id = id;
        this.pw = pw;
    }

    public String getId() {
        return id;
    }

    public String getPw() {
        return pw;
    }
}
