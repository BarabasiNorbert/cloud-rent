package bnorbert.rentcloud.fees;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Getter
@Setter
public class AdditionalFee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private double dogFee;
    private double catFee;
    private double dogRent;
    private double catRent;
    private double adminFee;
    private double gymMembershipPrice;
    private double assignedGarageParking;

}
