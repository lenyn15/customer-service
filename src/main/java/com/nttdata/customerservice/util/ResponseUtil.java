package com.nttdata.customerservice.util;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * @author Lenyn Smith Goicochea Arévalo
 */
public final class ResponseUtil {

    private ResponseUtil() {
    }

    public static Response getOkResponse( Map<?, ?> data ) {
        return Response.builder()
                .statusCode( HttpStatus.OK.value() )
                .httpStatus( HttpStatus.OK )
                .data( data )
                .build();
    }

    public static Response getCreatedResponse( Map<?, ?> data ) {
        return Response.builder()
                .statusCode( HttpStatus.CREATED.value() )
                .httpStatus( HttpStatus.CREATED )
                .data( data )
                .build();
    }

    public static Response getBadResponse( List<String> errors ) {
        return Response.builder()
                .statusCode( HttpStatus.BAD_REQUEST.value() )
                .httpStatus( HttpStatus.BAD_REQUEST )
                .errors( errors )
                .build();
    }

    public static ResponseEntity<Response> getRestResponse( Response response ) {
        ResponseEntity<Response> responseEntity = ResponseEntity.ok( response );

        if ( HttpStatus.CREATED.equals( response.getHttpStatus() ) ) {
            responseEntity = new ResponseEntity<>( response, HttpStatus.CREATED );
        }

        if ( Objects.nonNull( response.getErrors() ) ) {
            responseEntity = ResponseEntity.badRequest().body( response );
        }


        return responseEntity;
    }
}
