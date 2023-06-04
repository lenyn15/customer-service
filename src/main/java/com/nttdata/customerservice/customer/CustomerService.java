package com.nttdata.customerservice.customer;

import reactor.core.publisher.Mono;

/**
 * @author Lenyn Smith Goicochea Arévalo
 */
public interface CustomerService {

    /**
     * Get customer information with the specified document number
     *
     * @param nmDocument customer's document number
     * @return Mono<CustomerDTO>
     */
    Mono<CustomerDTO> getCustomerInfo( String nmDocument );

    /**
     * Register a new customer
     *
     * @param requestDTO Object with required fields filled
     * @return Mono<String>
     */
    Mono<String> addNewCustomer( Mono<CustomerDTO> requestDTO );
}
