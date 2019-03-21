package com.takeHospital.repository.repositoryScheme;

import com.takeHospital.domain.parametrsForScheme.NTISS;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NTISSRepository  extends JpaRepository<NTISS, Long> {
}
