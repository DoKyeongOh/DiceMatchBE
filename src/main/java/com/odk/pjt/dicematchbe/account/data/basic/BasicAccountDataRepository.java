package com.odk.pjt.dicematchbe.account.data.basic;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BasicAccountDataRepository extends JpaRepository<BasicAccountData, String> {

    @Override
    Optional<BasicAccountData> findById(String accountId);

    Optional<BasicAccountData> findByIdentityAndPwHash(String identity, String pwHash);

}
