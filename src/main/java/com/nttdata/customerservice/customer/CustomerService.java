package com.nttdata.customerservice.customer;

import reactor.core.publisher.Mono;

/**
 * @author Lenyn Smith Goicochea Arévalo
 */
public interface CustomerService {

    /**
     * Get customer information with the specified identifier
     *
     * @param idCustomer customer's identifier
     * @return Mono<Customer>
     */
    Mono<CustomerDTO> getCustomerInfo( String idCustomer );

    /**
     * Register a new customer
     *
     * @param requestDTO Object with required fields filled
     * @return Mono<Void>
     */
    Mono<Void> addNewCustomer( Mono<CustomerDTO> requestDTO );
}
