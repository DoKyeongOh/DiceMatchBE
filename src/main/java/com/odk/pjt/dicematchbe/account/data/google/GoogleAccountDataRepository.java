package com.odk.pjt.dicematchbe.account.data.google;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GoogleAccountDataRepository extends JpaRepository<GoogleAccountData, String> {

    @Override
    Optional<GoogleAccountData> findById(String accountId);

    Optional<GoogleAccountData> findByEmail(String email);

}
