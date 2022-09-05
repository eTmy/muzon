package com.etmy.onlinerpg;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Account {
    @JsonProperty("username")
    private String login;
    private String password;

    public Account(){
    }

    public Account(String login, String password) {
        this.login = login;
        this.password = password;
    }
}
