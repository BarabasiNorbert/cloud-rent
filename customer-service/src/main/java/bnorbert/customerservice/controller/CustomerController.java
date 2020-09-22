package bnorbert.customerservice.controller;

import bnorbert.customerservice.service.CustomerService;
import bnorbert.rentcloud.customer.transfer.CustomerResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

import static org.springframework.http.ResponseEntity.status;

@Slf4j
@CrossOrigin
@RestController
@RequestMapping("/services")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping(value = "/customers/{id}")
    public ResponseEntity<CustomerResponse> getCustomer(@PathVariable long id) {
        log.info("request came on " + LocalDateTime.now());
        return status(HttpStatus.OK).body(customerService.getCustomerId(id));
    }
}
