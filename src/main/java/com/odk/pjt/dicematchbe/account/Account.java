package com.odk.pjt.dicematchbe.account;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.Date;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class Account {
    @Id
    @Column(name = "account_id")
    public String accountId;

    @Column(name = "user_id")
    public String userId;

    @Column(name = "account_type")
    public AccountType type;

    @CreatedDate
    @Column(name = "createdDate")
    public Date createdDate;

    @Column(name = "account_active")
    public boolean active = true;
}
