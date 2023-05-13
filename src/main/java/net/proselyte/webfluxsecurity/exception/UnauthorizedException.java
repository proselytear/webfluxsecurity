package net.proselyte.webfluxsecurity.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.UNAUTHORIZED)
public class UnauthorizedException extends ApiException{
    public UnauthorizedException(String message) {
        super(message,"PROSELYTE_UNAUTHORIZED");
    }
}
