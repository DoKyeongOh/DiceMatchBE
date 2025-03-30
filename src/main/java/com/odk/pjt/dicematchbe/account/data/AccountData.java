package com.odk.pjt.dicematchbe.account.data;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;

@MappedSuperclass
public class AccountData {
    @Id
    @Column(name = "acconut_id", nullable = false, unique = true)
    public String accountId;
}
