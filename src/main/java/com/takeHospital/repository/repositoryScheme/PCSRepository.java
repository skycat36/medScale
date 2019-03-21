package com.takeHospital.repository.repositoryScheme;

import com.takeHospital.domain.parametrsForScheme.PCS;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PCSRepository extends JpaRepository<PCS, Long> {
}
