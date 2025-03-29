package com.odk.pjt.dicematchbe.account.data;

import jakarta.persistence.Column;

public class BasicAccountData extends AccountData {
    @Column(name = "identity")
    public String identity;

    @Column(name = "pwHash")
    public String pwHash;
}
