package com.takeHospital.repository;

import com.takeHospital.domain.parametrsForScheme.ParamScheme;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SchemeRepository extends JpaRepository<ParamScheme, Long> {
    ParamScheme findByNameSheme(String nameScheme);
}
