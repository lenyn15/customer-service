package com.nttdata.customerservice.customer;

import com.nttdata.customerservice.enums.CustomerType;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * @author Lenyn Smith Goicochea Arévalo
 */
@Getter
@Setter
@Document( collection = "customers" )
public class Customer implements Serializable {

    /**
     * Document's primary key
     */
    @Id
    private String idCustomer;

    /**
     * Customer's name
     */
    @Field( name = "DS_NAME" )
    private String dsName;

    /**
     * Customer's document number
     */
    @Field( name = "NM_DOCUMENT" )
    private String nmDocument;

    /**
     * Type of customer
     */
    @Field( name = "CUSTOMER_TYPE" )
    private CustomerType customerType;

    /**
     * Customer registration date
     */
    @Field( name = "CREATED_AT" )
    private LocalDate createdAt;
}
