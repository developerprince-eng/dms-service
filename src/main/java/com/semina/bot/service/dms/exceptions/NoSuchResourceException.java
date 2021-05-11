package com.semina.bot.service.dms.exceptions;

import java.util.NoSuchElementException;

public class NoSuchResourceException extends NoSuchElementException {
    public NoSuchResourceException(String errorMessage){
        super(errorMessage);
    }
}
