package com.odk.pjt.dicematchbe.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    @Override
    Optional<User> findById(String userId);

    @Override
    List<User> findAll();



}
