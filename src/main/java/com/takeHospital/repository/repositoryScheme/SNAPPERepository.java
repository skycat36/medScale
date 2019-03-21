package com.takeHospital.repository.repositoryScheme;

import com.takeHospital.domain.parametrsForScheme.SNAPPE;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SNAPPERepository  extends JpaRepository<SNAPPE, Long> {
}
