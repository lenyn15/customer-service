package com.nttdata.customerservice.rest;

import com.nttdata.customerservice.customer.CustomerDTO;
import com.nttdata.customerservice.util.Response;
import com.nttdata.customerservice.util.ResponseUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

/**
 * @author Lenyn Smith Goicochea Arévalo
 */
@RestController
@RequestMapping( "/customer" )
@RequiredArgsConstructor
@Tag( name = "customer", description = "the customer API" )
public class CustomerRest {

    @Qualifier( "customerResource" )
    private final CustomerResource customerResource;

    @Operation( operationId = "getCustomerInformation",
            summary = "Get information related to one customer",
            description = "Get information related to one customer by his document number",
            tags = { "customer" },
            responses = {
                    @ApiResponse( responseCode = "200", description = "Successful operation", content = @Content( mediaType = "application/json", schema = @Schema( implementation = Response.class ) ) ),
                    @ApiResponse( responseCode = "400", description = "Invalid document number supplied", content = @Content( mediaType = "application/json", schema = @Schema( implementation = Response.class ) ) ),
                    @ApiResponse( responseCode = "404", description = "Customer not found", content = @Content( mediaType = "application/json", schema = @Schema( implementation = Response.class ) ) )
            } )
    @GetMapping( "{nmDocument}" )
    public Mono<ResponseEntity<Response>> getCustomerInformation(
            @Parameter( name = "nmDocument", description = "Customer's document number", required = true )
            @PathVariable String nmDocument
    ) {
        return this.customerResource.getCustomerInformation( nmDocument ).map( ResponseUtil::getRestResponse );
    }

    @Operation( operationId = "addNewCustomer",
            summary = "Save a new customer",
            tags = { "customer" },
            responses = {
                    @ApiResponse( responseCode = "201", description = "Successful operation", content = @Content( mediaType = "application/json", schema = @Schema( implementation = Response.class ) ) )
            } )
    @PostMapping
    public Mono<ResponseEntity<Response>> addNewCustomer(
            @Parameter( name = "CustomerDTO", required = true )
            @Valid @RequestBody Mono<CustomerDTO> requestDTO
    ) {
        return this.customerResource.addNewCustomer( requestDTO ).map( ResponseUtil::getRestResponse );
    }
}
