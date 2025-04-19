package com.odk.pjt.dicematchbe.account;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.Date;

@Data
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class Account {
    @Id
    @Column(name = "account_id")
    @GeneratedValue(strategy = GenerationType.UUID)
    private String accountId;

    @Column(name = "user_id")
    private String userId;

    @Column(name = "account_type")
    private AccountType type;

    @CreatedDate
    @Column(name = "createdDate")
    private Date createdDate;

    @Column(name = "account_active")
    private boolean active = true;
}
