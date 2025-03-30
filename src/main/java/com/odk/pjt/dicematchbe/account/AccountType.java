package com.odk.pjt.dicematchbe.account;

import com.odk.pjt.dicematchbe.account.data.AccountData;
import com.odk.pjt.dicematchbe.account.data.basic.BasicAccountData;
import com.odk.pjt.dicematchbe.account.data.google.GoogleAccountData;

public enum AccountType {
    BASIC("BasicAccountDataService"),
    GOOGLE("GoogleAccountDataService"),
    NONE("None");

    private final String serviceName;

    AccountType(String serviceName) {
        this.serviceName = serviceName;
    }

    public static AccountType fromData(AccountData data) {
        if (data instanceof BasicAccountData) {
            return BASIC;
        }

        if (data instanceof GoogleAccountData) {
            return GOOGLE;
        }

        return NONE;
    }

    public String getServiceName() {
        return serviceName;
    }
}
