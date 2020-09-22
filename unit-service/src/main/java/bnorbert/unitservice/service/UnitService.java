package bnorbert.unitservice.service;

import bnorbert.rentcloud.unit.Unit;
import bnorbert.unitservice.exeption.ResourceNotFoundException;
import bnorbert.unitservice.repository.UnitRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UnitService {

    private final UnitRepository unitRepository;

    public UnitService(UnitRepository unitRepository) {
        this.unitRepository = unitRepository;
    }

    public Unit getUnitId(long id){
        log.info("Retrieving unit {}", id);
        return unitRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Unit " + id + "not found"));
    }
}
