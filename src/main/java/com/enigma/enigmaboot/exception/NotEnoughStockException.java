package com.enigma.enigmaboot.exception;

import org.hibernate.service.spi.ServiceException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.CONFLICT)
public class NotEnoughStockException extends ServiceException {
    public NotEnoughStockException(String message) {
        super(message);
    }
}
