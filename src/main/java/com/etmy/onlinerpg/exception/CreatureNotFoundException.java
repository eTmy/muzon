package com.etmy.onlinerpg.exception;

import javax.servlet.http.HttpServlet;

public class CreatureNotFoundException extends RuntimeException {

    public CreatureNotFoundException(String message) {
        super(message);
    }
}
