package com.odk.pjt.dicematchbe.account;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@EntityListeners(AuditingEntityListener.class)
@Getter @Setter
public class Account {
    @Id
    @Column(name = "account_id")
    private String accountId;

    @Column(name = "user_id")
    private String userId;

    @Column(name = "account_type")
    private AccountType type;

    @Column(name = "created_time")
    private long createdTime;

    @Column(name = "account_active")
    private boolean active = true;
}
