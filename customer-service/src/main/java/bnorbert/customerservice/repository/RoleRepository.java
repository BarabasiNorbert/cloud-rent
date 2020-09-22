package bnorbert.customerservice.repository;

import bnorbert.rentcloud.customer.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
}
