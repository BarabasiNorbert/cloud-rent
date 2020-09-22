package bnorbert.customerservice.service;

import bnorbert.customerservice.exeption.ResourceNotFoundException;
import bnorbert.customerservice.mapper.CustomerMapper;
import bnorbert.customerservice.repository.CustomerRepository;
import bnorbert.rentcloud.customer.Customer;
import bnorbert.rentcloud.customer.transfer.CustomerResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    public CustomerService(CustomerRepository customerRepository, CustomerMapper customerMapper) {
        this.customerRepository = customerRepository;
        this.customerMapper = customerMapper;
    }

    @Transactional
    public CustomerResponse getCustomerId(long id){
        log.info("Retrieving customer {}", id);
        Customer customer = customerRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Customer" + id + "not found"));

        return customerMapper.mapToDto(customer);
    }
}
