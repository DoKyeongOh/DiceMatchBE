package com.odk.pjt.dicematchbe.account.basic;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BasicAccountInfoRepository extends JpaRepository<BasicAccountInfo, String> {
    Optional<BasicAccountInfo> findByIdentityAndPasswordHash(String identity, String passwordHash);
    Optional<BasicAccountInfo> findByIdentity(String identity);
}
