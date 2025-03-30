package com.odk.pjt.dicematchbe.account.data.google;

import com.odk.pjt.dicematchbe.account.data.AccountDataService;
import org.springframework.stereotype.Service;

@Service
public class GoogleAccountDataService implements AccountDataService<GoogleAccountData> {
    @Override
    public GoogleAccountData register(GoogleAccountData data) {
        return null;
    }

    @Override
    public GoogleAccountData update(GoogleAccountData data) {
        return null;
    }

    @Override
    public String delete(String accountId) {
        return "";
    }
}
