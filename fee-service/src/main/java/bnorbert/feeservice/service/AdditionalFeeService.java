package bnorbert.feeservice.service;

import bnorbert.feeservice.exeption.ResourceNotFoundException;
import bnorbert.feeservice.repository.AdditionalFeeRepository;
import bnorbert.rentcloud.fees.AdditionalFee;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class AdditionalFeeService {

    private final AdditionalFeeRepository feeRepository;

    public AdditionalFeeService(AdditionalFeeRepository feeRepository) {
        this.feeRepository = feeRepository;
    }

    public AdditionalFee getFee(long id){
        log.info("Retrieving fee {}", id);
        return feeRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Fee " + id + "not found"));
    }
}
