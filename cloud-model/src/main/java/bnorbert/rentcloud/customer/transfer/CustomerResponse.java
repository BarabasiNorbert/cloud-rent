package bnorbert.rentcloud.customer.transfer;

import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
public class CustomerResponse {

    private Long id;
    private String firstName;
    private String lastName;
    private Set<RoleResponse> roles = new HashSet<>();

}
