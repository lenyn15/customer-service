package com.nttdata.customerservice.customer;

import com.nttdata.customerservice.exception.RequestException;
import com.nttdata.customerservice.mapper.CustomerToDTO;
import com.nttdata.customerservice.mapper.CustomerToEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;

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
    public Mono<CustomerDTO> getCustomerInfo( String nmDocument ) {
        return this.customerRepository.findByNmDocument( nmDocument )
                .map( CustomerToDTO.INSTANCE )
                .switchIfEmpty( Mono.error( new RequestException( "No se encontró al cliente" ) ) );
    }

    /**
     *
     */
    @Override
    public Mono<String> addNewCustomer( Mono<CustomerDTO> requestDTO ) {
        return requestDTO.flatMap( customerDTO -> {
            customerDTO.setCreatedAt( LocalDate.now() );
            return this.customerRepository.findByDsName( customerDTO.getDsName() ).hasElement()
                    .zipWith( this.customerRepository.findByNmDocument( customerDTO.getNmDocument() ).hasElement() )
                    .flatMap( elements -> {
                        this.validate( elements );
                        return this.customerRepository.save( CustomerToEntity.INSTANCE.apply( customerDTO ) )
                                .map( Customer::getIdCustomer );
                    } );
        } );
    }

    private void validate( Tuple2<Boolean, Boolean> elements ) {
        boolean existByDsName = elements.getT1();
        boolean existByNmDocument = elements.getT2();
        if ( Boolean.TRUE.equals( existByDsName ) ) {
            throw new RequestException( "El cliente con ese nombre ya existe" );
        }

        if ( Boolean.TRUE.equals( existByNmDocument ) ) {
            throw new RequestException( "El cliente con ese numero de documento ya existe" );
        }
    }
}
