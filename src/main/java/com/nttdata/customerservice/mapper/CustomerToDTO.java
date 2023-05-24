package com.nttdata.customerservice.mapper;

import com.nttdata.customerservice.customer.Customer;
import com.nttdata.customerservice.customer.CustomerDTO;
import org.springframework.beans.BeanUtils;

import java.util.Objects;
import java.util.function.Function;

/**
 * @author Lenyn Smith Goicochea Arévalo
 */
public enum CustomerToDTO implements Function<Customer, CustomerDTO> {

    INSTANCE;

    /**
     * Convert entity to DTO.
     *
     * @param entity to convert
     * @return instance of CustomerDTO
     */
    @Override
    public CustomerDTO apply( Customer entity ) {
        CustomerDTO dto = new CustomerDTO();

        if ( Objects.nonNull( entity ) ) {
            BeanUtils.copyProperties( entity, dto );

            if ( Objects.nonNull( entity.getCustomerType() ) ) {
                dto.setCustomerType( entity.getCustomerType().name() );
            }
        }
        return dto;
    }
}
