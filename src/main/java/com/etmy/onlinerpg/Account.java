package com.etmy.onlinerpg;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Account {
    //@JsonProperty("username")
    private String username;
    private String password;

    public Account(){
    }

    public Account(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
