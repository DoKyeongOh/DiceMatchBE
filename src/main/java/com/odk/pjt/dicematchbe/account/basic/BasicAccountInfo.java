package com.odk.pjt.dicematchbe.account.basic;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "basic_account_info")
public class BasicAccountInfo {
    @Id
    @Column(name = "account_id")
    private String accountId;

    @Column(name = "identity")
    private String identity;

    @Column(name = "password_hash")
    private String passwordHash;
}
