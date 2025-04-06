package com.odk.pjt.dicematchbe.account.basic;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BasicAccountRepository extends JpaRepository<BasicAccount, String> {

    @Override
    Optional<BasicAccount> findById(String accountId);

    Optional<BasicAccount> findByIdentityAndPassword(String identity, String pwHash);

}
