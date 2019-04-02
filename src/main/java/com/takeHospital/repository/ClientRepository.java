package com.takeHospital.repository;

import com.takeHospital.domain.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {

    @Query(value = "select DISTINCT opn from Client")
    List<Long> findAllOpn();

    List<Client> findByFam(String fam);

    List<Client> findByOpn(Long opn);

    List<Client> findByBirthdate(LocalDate localDate);

    void deleteClientsByOpn(Long opn);
}
