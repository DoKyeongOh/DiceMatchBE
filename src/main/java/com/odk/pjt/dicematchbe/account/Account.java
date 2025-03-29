package com.odk.pjt.dicematchbe.account;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.util.Date;

@Entity(name = "account")
public class Account {
    @Id
    @Column(name = "acconut_id", nullable = false, unique = true)
    @GeneratedValue(strategy = GenerationType.UUID)
    public String id;

    @Column(name = "account_type")
    public AccountType type;

    @Column(name = "createdDate")
    public Date createdDate;

    @Column(name = "account_active")
    public boolean active;
}
