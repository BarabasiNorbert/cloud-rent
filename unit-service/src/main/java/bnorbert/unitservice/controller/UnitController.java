package bnorbert.unitservice.controller;

import bnorbert.rentcloud.unit.Unit;
import bnorbert.unitservice.service.UnitService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

import static org.springframework.http.ResponseEntity.status;

@Slf4j
@CrossOrigin
@RestController
@RequestMapping("/services")
public class UnitController {

    private final UnitService unitService;

    public UnitController(UnitService unitService) {
        this.unitService = unitService;
    }

    @GetMapping(value = "/units/{id}")
    public ResponseEntity<Unit> getFee(@PathVariable long id) {
        log.info("request came on " + LocalDateTime.now());
        return status(HttpStatus.OK).body(unitService.getUnitId(id));
    }
}
