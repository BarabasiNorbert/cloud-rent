package bnorbert.rentservice.repository;

import bnorbert.rentcloud.rent.Rent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RentRepository extends JpaRepository<Rent, Long> {
}
