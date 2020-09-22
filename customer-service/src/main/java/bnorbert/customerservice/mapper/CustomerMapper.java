package bnorbert.customerservice.mapper;

import bnorbert.rentcloud.customer.Customer;
import bnorbert.rentcloud.customer.Role;
import bnorbert.rentcloud.customer.transfer.CustomerResponse;
import bnorbert.rentcloud.customer.transfer.RoleResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public abstract class CustomerMapper {

    @Mapping(target = "id", source = "id")
    @Mapping(target = "firstName", source = "customer.firstName")
    @Mapping(target = "lastName", source = "customer.lastName")
    public abstract CustomerResponse mapToDto(Customer customer);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "name", source = "role.name")
    public abstract RoleResponse mapToDto(Role role);
}
