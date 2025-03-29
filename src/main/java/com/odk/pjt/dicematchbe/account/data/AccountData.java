package com.odk.pjt.dicematchbe.account.data;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity(name = "account_data")
public class AccountData {
    @Id
    @Column(name = "acconut_id", nullable = false, unique = true)
    public String accountId;
}
