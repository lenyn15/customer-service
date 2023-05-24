package com.nttdata.customerservice.mapper;

import com.nttdata.customerservice.customer.Customer;
import com.nttdata.customerservice.customer.CustomerDTO;
import com.nttdata.customerservice.enums.CustomerType;
import com.nttdata.customerservice.util.Constants;
import org.springframework.beans.BeanUtils;

import java.util.Objects;
import java.util.function.Function;

/**
 * @author Lenyn Smith Goicochea Arévalo
 */
public enum CustomerToEntity implements Function<CustomerDTO, Customer> {

    INSTANCE;

    /**
     * Convert DTO to entity.
     *
     * @param dto to convert
     * @return instance of Customer
     */
    @Override
    public Customer apply( CustomerDTO dto ) {
        Customer entity = null;

        if ( Objects.nonNull( dto ) ) {
            entity = new Customer();
            BeanUtils.copyProperties( dto, entity );

            if ( Objects.nonNull( dto.getType() ) ) {
                this.assignType( entity, dto.getType() );
            }
        }

        return entity;
    }

    private void assignType( Customer entity, Integer type ) {
        entity.setCustomerType( CustomerType.PERSONAL );
        if ( Constants.CustomerType.BUSINESS.equals( type ) ) {
            entity.setCustomerType( CustomerType.BUSINESS );
        }
    }
}
