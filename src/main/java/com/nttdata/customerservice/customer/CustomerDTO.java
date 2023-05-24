package com.nttdata.customerservice.customer;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;

/**
 * @author Lenyn Smith Goicochea Arévalo
 */
@Getter
@Setter
@JsonInclude( Include.NON_NULL )
public class CustomerDTO implements Serializable {

    /**
     * Document's primary key
     */
    private String idCustomer;

    /**
     * Customer's name
     */
    @NotEmpty( message = "El nombre del cliente no puede estar en blanco" )
    private String dsName;

    /**
     * Type of customer
     */
    @NotNull( message = "Debe elegir el tipo de cliente" )
    private Integer type;

    /**
     * Type of customer description
     */
    private String customerType;

    /**
     * Customer registration date
     */
    private LocalDate createdAt;
}
