package com.odk.pjt.dicematchbe.account.basic;

import com.odk.pjt.dicematchbe.account.Account;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = false)
@Entity(name = "basic_account")
public class BasicAccount extends Account {

    @Column(name = "identity")
    public String identity;

    @Column(name = "password")
    public String password;
}
