package com.takeHospital.repository;

import com.takeHospital.domain.Worker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WorkerRepository extends JpaRepository<Worker, Long> {
    Worker findByLogin(String login);

    List<Worker> findByFam(String fam);

}
