package bnorbert.feeservice.repository;

import bnorbert.rentcloud.fees.AdditionalFee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdditionalFeeRepository extends JpaRepository<AdditionalFee, Long> {
}
