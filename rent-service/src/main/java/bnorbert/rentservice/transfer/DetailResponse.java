package bnorbert.rentservice.transfer;

import bnorbert.rentcloud.customer.transfer.CustomerResponse;
import bnorbert.rentcloud.fees.AdditionalFee;
import bnorbert.rentcloud.rent.Rent;
import bnorbert.rentcloud.unit.Unit;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DetailResponse implements Response {

    Rent rent;
    CustomerResponse customerResponse;
    AdditionalFee additionalFee;
    Unit unit;

    public DetailResponse(Rent rent, CustomerResponse customerResponse, AdditionalFee additionalFee, Unit unit) {
        this.rent = rent;
        this.customerResponse = customerResponse;
        this.additionalFee = additionalFee;
        this.unit = unit;
    }
}
