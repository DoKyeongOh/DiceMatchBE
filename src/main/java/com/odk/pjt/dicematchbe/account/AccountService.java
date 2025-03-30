package com.odk.pjt.dicematchbe.account;

import com.odk.pjt.dicematchbe.account.data.AccountData;
import com.odk.pjt.dicematchbe.account.data.AccountDataService;
import com.odk.pjt.dicematchbe.exception.DiceMatchException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Map;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private Map<String, AccountDataService> dataServiceMap;

    public Account register(AccountData accountData) throws DiceMatchException {
        Account account = new Account();
        account.type = AccountType.fromData(accountData);
        account.active = true;
        account.createdDate = new Date();
        Account newAccount = accountRepository.save(account);

        AccountData register = dataServiceMap.get(account.type.getServiceName()).register(accountData);

        return newAccount;
    }

    public Account setActive(String accountId) {
        return null;
    }

}
