package com.keys.api.exceptions;

import lombok.Getter;

@Getter
public class UserAlreadyException extends RuntimeException {
    private String msg;

    public UserAlreadyException ( String msg ) {
        super( msg );
        this.msg = msg;
    }
}
