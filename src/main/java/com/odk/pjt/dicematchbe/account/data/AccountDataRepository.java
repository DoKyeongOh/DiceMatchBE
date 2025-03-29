package com.odk.pjt.dicematchbe.account.data;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountDataRepository<T extends AccountData> extends JpaRepository<T, String> {

    @Override
    Optional<T> findById(String accountId);

}
