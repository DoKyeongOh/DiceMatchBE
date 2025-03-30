package com.odk.pjt.dicematchbe.account.data.basic;

import com.odk.pjt.dicematchbe.account.data.AccountDataService;
import com.odk.pjt.dicematchbe.util.HashEncryptionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BasicAccountDataService implements AccountDataService<BasicAccountData> {

    private final BasicAccountDataRepository repository;

    @Autowired
    public BasicAccountDataService(BasicAccountDataRepository repository) {
        this.repository = repository;
    }

    @Override
    public BasicAccountData register(BasicAccountData data) {
//        data.pwHash = HashEncryptionUtil.encrypt("rsa", data.pwHash);
        Optional<BasicAccountData> optionalData = repository.findByIdentityAndPwHash(data.identity, data.pwHash);
        return null;
    }

    @Override
    public BasicAccountData update(BasicAccountData data) {
        return null;
    }

    @Override
    public String delete(String accountId) {
        return "";
    }
}
