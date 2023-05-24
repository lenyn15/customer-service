package com.nttdata.customerservice.util;

import com.nttdata.customerservice.exception.RequestException;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.web.bind.support.WebExchangeBindException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Lenyn Smith Goicochea Arévalo
 */
public final class Util {

    private Util() {
    }

    /**
     * Pass to a map any type of object like List or Class
     *
     * @param object the reference object to pass to the map
     * @return Map<String, Object>
     */
    public static Map<String, Object> getData( Object object ) {
        Map<String, Object> data = new HashMap<>();
        data.put( Constants.Key.INFO, object );

        return data;
    }

    /**
     * Pass to a map the message to return
     *
     * @param message String with success message
     * @return Map<String, String>
     */
    public static Map<String, String> getMessageSuccess( String message ) {
        Map<String, String> data = new HashMap<>();
        data.put( Constants.Key.MESSAGE, message );

        return data;
    }

    /**
     * Cast errors from fields validation
     *
     * @param throwable Class exception with message errors
     * @return Mono<List<String>>
     */
    public static Mono<List<String>> getErrorMessages( Throwable throwable ) {
        if ( throwable instanceof RequestException ) {
            return Mono.just( List.of( throwable.getMessage() ) );
        }

        return Mono.just( throwable ).cast( WebExchangeBindException.class )
                .flatMap( e -> Mono.just( e.getFieldErrors() ) )
                .flatMapMany( Flux::fromIterable )
                .map( DefaultMessageSourceResolvable::getDefaultMessage )
                .collectList();
    }
}
