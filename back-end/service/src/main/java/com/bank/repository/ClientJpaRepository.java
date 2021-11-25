package com.bank.repository;

import com.bank.model.AccountEntity;
import com.bank.model.ClientEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientJpaRepository extends JpaRepository<ClientEntity, Long> {
    ClientEntity findByName(String name);

    @Query(value = "select * from client where id = :clientId",nativeQuery = true)
    ClientEntity findOwnerInAccount(@Param("clientId") long clientID);
}
