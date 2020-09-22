package bnorbert.rentcloud.rent;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class Rent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long rentId;
    private Long customerId;
    private Long additionalFeeId;
    private Long unitId;
    private double price;
    private LocalDateTime createdDate;
    private LocalDateTime terminationDate;
}
