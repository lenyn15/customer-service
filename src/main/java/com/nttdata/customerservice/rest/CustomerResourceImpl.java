package com.nttdata.customerservice.rest;

import com.nttdata.customerservice.customer.CustomerDTO;
import com.nttdata.customerservice.customer.CustomerService;
import com.nttdata.customerservice.util.Response;
import com.nttdata.customerservice.util.ResponseUtil;
import com.nttdata.customerservice.util.Util;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.Map;

/**
 * @author Lenyn Smith Goicochea Arévalo
 */
@Component( "customerResource" )
@RequiredArgsConstructor
public class CustomerResourceImpl implements CustomerResource {

    @Qualifier( "customerService" )
    private final CustomerService customerService;

    /**
     *
     */
    @Override
    public Mono<Response> getCustomerInformation( String nmDocument ) {
        return this.customerService.getCustomerInfo( nmDocument )
                .map( customerDTO -> {
                    Map<String, Object> data = Util.getData( customerDTO );
                    return ResponseUtil.getOkResponse( data );
                } );
    }

    /**
     *
     */
    @Override
    public Mono<Response> addNewCustomer( Mono<CustomerDTO> requestDTO ) {
        return this.customerService.addNewCustomer( requestDTO )
                .map( idCustomer -> {
                    Map<String, Object> data = Util.getData( idCustomer );
                    return ResponseUtil.getCreatedResponse( data );
                } )
                .onErrorResume( throwable -> Util.getErrorMessages( throwable )
                        .flatMap( errors -> Mono.just( ResponseUtil.getBadResponse( errors ) ) )
                );
    }
}
