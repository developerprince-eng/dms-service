package com.semina.bot.service.dms.exceptions;

public class NoAvailableResourceException extends NullPointerException {
    public NoAvailableResourceException(String errorMesage){
        super(errorMesage);
    }
}
