package com.takeHospital.repository.repositoryScheme;

import com.takeHospital.domain.parametrsForScheme.SOFA;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SOFARepository extends JpaRepository<SOFA, Long> {
}
