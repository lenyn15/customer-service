package com.nttdata.customerservice.rest;

import com.nttdata.customerservice.customer.CustomerDTO;
import com.nttdata.customerservice.util.Response;
import reactor.core.publisher.Mono;

/**
 * @author Lenyn Smith Goicochea Arévalo
 */
public interface CustomerResource {

    /**
     * Transform the customer's information into a custom response
     *
     * @param identifier customer's identifier like his ID or document number
     * @return Mono<Response>
     */
    Mono<Response> getCustomerInformation( String identifier );

    /**
     * When the customer is saved, the success message is passed to the
     * custom Response
     *
     * @param requestDTO Object with required fields filled
     * @return Mono<Response>
     */
    Mono<Response> addNewCustomer( Mono<CustomerDTO> requestDTO );
}
