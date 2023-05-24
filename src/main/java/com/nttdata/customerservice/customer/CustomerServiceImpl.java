package com.nttdata.customerservice.customer;

import com.nttdata.customerservice.exception.RequestException;
import com.nttdata.customerservice.mapper.CustomerToDTO;
import com.nttdata.customerservice.mapper.CustomerToEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.time.LocalDate;

/**
 * @author Lenyn Smith Goicochea Arévalo
 */
@Slf4j
@Service( "customerService" )
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    @Qualifier( "customerRepository" )
    private final CustomerRepository customerRepository;

    /**
     *
     */
    @Override
    public Mono<CustomerDTO> getCustomerInfo( String idCustomer ) {
        return this.customerRepository.findById( idCustomer )
                .map( CustomerToDTO.INSTANCE )
                .switchIfEmpty( Mono.error( new RequestException( "No se encontró al cliente" ) ) );
    }

    /**
     *
     */
    @Override
    public Mono<Void> addNewCustomer( Mono<CustomerDTO> requestDTO ) {
        return requestDTO.flatMap( customerDTO -> {
            customerDTO.setCreatedAt( LocalDate.now() );
            String customerName = customerDTO.getDsName();

            return this.customerRepository.findByDsName( customerName )
                    .flatMap( existingCustomer -> {
                        throw new RequestException( "Ya existe un cliente con ese nombre" );
                    } )
                    .switchIfEmpty( this.customerRepository.save( CustomerToEntity.INSTANCE.apply( customerDTO ) ) )
                    .then();
        } );
    }
}
