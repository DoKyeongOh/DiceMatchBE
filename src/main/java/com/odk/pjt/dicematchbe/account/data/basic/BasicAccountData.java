package com.odk.pjt.dicematchbe.account.data.basic;

import com.odk.pjt.dicematchbe.account.data.AccountData;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;

@Entity(name = "basic_account_data")
public class BasicAccountData extends AccountData {
    @Column(name = "identity")
    public String identity;

    @Column(name = "password")
    public String password;

    @Override
    public String toString() {
        return "BasicAccountData{" +
                "identity='" + identity + '\'' +
                ", password='" + password + '\'' +
                ", accountId='" + accountId + '\'' +
                '}';
    }
}
