package bnorbert.customerservice.repository;

import bnorbert.rentcloud.customer.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
