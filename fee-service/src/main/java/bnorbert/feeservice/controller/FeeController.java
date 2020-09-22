package bnorbert.feeservice.controller;

import bnorbert.feeservice.service.AdditionalFeeService;
import bnorbert.rentcloud.fees.AdditionalFee;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

import static org.springframework.http.ResponseEntity.status;

@CrossOrigin
@RestController
@RequestMapping("/services")
@Slf4j
public class FeeController {

    private final AdditionalFeeService additionalFeeService;

    public FeeController(AdditionalFeeService additionalFeeService) {
        this.additionalFeeService = additionalFeeService;
    }

    @GetMapping(value = "/fees/{id}")
    public ResponseEntity<AdditionalFee> getFee(@PathVariable long id) {
        log.info("request came on " + LocalDateTime.now() );
        return status(HttpStatus.OK).body(additionalFeeService.getFee(id));
    }
}
