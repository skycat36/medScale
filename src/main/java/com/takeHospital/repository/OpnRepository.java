package com.takeHospital.repository;

import com.takeHospital.domain.Opn;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OpnRepository extends JpaRepository<Opn, Long> {
}
