package com.odk.pjt.dicematchbe.account.data;

import jakarta.persistence.Column;

public class GoogleAccountData extends AccountData {
    @Column(name = "email")
    public String email;
}
