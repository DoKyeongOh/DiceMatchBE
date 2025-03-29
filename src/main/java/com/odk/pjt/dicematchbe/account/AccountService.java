package com.odk.pjt.dicematchbe.account;

import com.odk.pjt.dicematchbe.account.data.AccountData;
import com.odk.pjt.dicematchbe.account.data.AccountDataRepository;
import com.odk.pjt.dicematchbe.account.data.BasicAccountDataRepository;
import com.odk.pjt.dicematchbe.account.data.GoogleAccountDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class AccountService {

    @Autowired
    private Map<String, AccountDataRepository> dataRepositoryMap;

    @Autowired
    private AccountRepository accountRepository;

    public Account register(AccountData accountData) {
        return null;
    }

    public Account setActive(String accountId) {
        return null;
    }

    private AccountData getAccountData(String accountId) throws Exception {
        Account account = accountRepository.findById(accountId).orElseThrow(() ->
                new Exception("Account not found: " + accountId));

        JpaRepository<AccountData, String> dataRepository = null;

        switch (account.type) {
            case BASIC -> {
                dataRepository = dataRepositoryMap.get(BasicAccountDataRepository.class.getName());
            }
            case GOOGLE -> {
                dataRepository = dataRepositoryMap.get(GoogleAccountDataRepository.class.getName());
            }
            default -> {
                throw new Exception("unknown input account type");
            }
        }

        if (dataRepository == null) {
            throw new Exception("account data repository not found: " + accountId);
        }

        return dataRepository.findById(accountId).orElseThrow(() ->
                new Exception("account data not found: " + accountId));
    }

}
