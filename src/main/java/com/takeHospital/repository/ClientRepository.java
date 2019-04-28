package com.takeHospital.repository;

import com.takeHospital.domain.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {

    List<Client> findByFam(String fam);

    List<Client> findByOpn(Long opn);

    List<Client> findByBirthdate(LocalDate localDate);

    @Transactional
    void deleteAllByOpn(Long opn);
}
