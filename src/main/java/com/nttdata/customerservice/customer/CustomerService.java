package com.nttdata.customerservice.customer;

import reactor.core.publisher.Mono;

/**
 * @author Lenyn Smith Goicochea Arévalo
 */
public interface CustomerService {

    /**
     * Get customer information with the specified identifier
     *
     * @param identifier customer's identifier like his ID or document number
     * @return Mono<CustomerDTO>
     */
    Mono<CustomerDTO> getCustomerInfo( String identifier );

    /**
     * Register a new customer
     *
     * @param requestDTO Object with required fields filled
     * @return Mono<String>
     */
    Mono<String> addNewCustomer( Mono<CustomerDTO> requestDTO );
}
