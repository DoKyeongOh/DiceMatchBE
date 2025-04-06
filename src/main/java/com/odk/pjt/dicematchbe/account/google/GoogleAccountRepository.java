package com.odk.pjt.dicematchbe.account.google;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GoogleAccountRepository extends JpaRepository<GoogleAccount, String> {

    @Override
    Optional<GoogleAccount> findById(String accountId);

    Optional<GoogleAccount> findByEmail(String email);

}
