package com.bank.repository;

import com.bank.model.AccountEntity;
import com.bank.model.ClientEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountJpaRepository extends JpaRepository<AccountEntity, Long> {

    @Modifying
    @Query(value = "update account a  set a.balance =  :depot where a.owner_id = :clientId",nativeQuery = true)
    void makeDepotInAccount(@Param("clientId") String code, @Param("depot") double plus);




    AccountEntity findAccountEntityByCode(String code);
}
