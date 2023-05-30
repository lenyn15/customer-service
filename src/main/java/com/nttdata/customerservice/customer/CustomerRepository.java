package com.nttdata.customerservice.customer;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

/**
 * @author Lenyn Smith Goicochea Arévalo
 */
@Repository( "customerRepository" )
public interface CustomerRepository extends ReactiveMongoRepository<Customer, String> {

    Mono<Customer> findByDsName( String dsName );

    Mono<Customer> findByNmDocument( String nmDocument );
}
