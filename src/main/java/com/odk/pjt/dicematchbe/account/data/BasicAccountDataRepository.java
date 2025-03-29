package com.odk.pjt.dicematchbe.account.data;

import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BasicAccountDataRepository extends AccountDataRepository<BasicAccountData> {

    Optional<BasicAccountData> findByIdentityAndPwHash(String identity, String pwHash);

}
