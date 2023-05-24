package com.nttdata.customerservice.exception;

import com.nttdata.customerservice.util.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;

/**
 * @author Lenyn Smith Goicochea Arévalo
 */
@ControllerAdvice
public class DefaultExceptionHandler {

    @ExceptionHandler( RuntimeException.class )
    public ResponseEntity<Response> handleException( RuntimeException e ) {
        Response apiError = Response.builder()
                .statusCode( HttpStatus.BAD_REQUEST.value() )
                .httpStatus( HttpStatus.BAD_REQUEST )
                .errors( List.of( e.getMessage() ) )
                .build();

        return new ResponseEntity<>( apiError, HttpStatus.BAD_REQUEST );
    }

}
