package bnorbert.unitservice.repository;

import bnorbert.rentcloud.unit.Unit;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UnitRepository extends JpaRepository<Unit, Long> {
}
