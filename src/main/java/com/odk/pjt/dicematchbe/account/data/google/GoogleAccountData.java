package com.odk.pjt.dicematchbe.account.data.google;

import com.odk.pjt.dicematchbe.account.data.AccountData;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;

@Entity(name = "google_account_data")
public class GoogleAccountData extends AccountData {
    @Column(name = "email")
    public String email;
}
