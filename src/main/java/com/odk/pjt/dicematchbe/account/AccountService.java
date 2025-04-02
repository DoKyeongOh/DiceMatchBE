package com.odk.pjt.dicematchbe.account;

import com.odk.pjt.dicematchbe.account.data.AccountData;
import com.odk.pjt.dicematchbe.account.data.basic.BasicAccountData;
import com.odk.pjt.dicematchbe.account.data.google.GoogleAccountData;
import com.odk.pjt.dicematchbe.exception.BadEntityInputException;
import com.odk.pjt.dicematchbe.exception.DiceMatchException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    public Account add(AccountType type) throws BadEntityInputException {
        if (type == null) {
            throw new BadEntityInputException("account add fail: type is null");
        }

        Account account = new Account();
        account.type = type;
        account.createdDate = new Date();
        account.active = true;

        return accountRepository.save(account);
    }

    public Account setActive(String accountId, boolean value) throws DiceMatchException {
        Account account = accountRepository.findById(accountId).orElseThrow(() ->
                new DiceMatchException(String.format("setActive %b fail: %s", value, accountId)));

        account.active = value;
        return accountRepository.save(account);
    }


    private static AccountType getAccountType(AccountData data) {
        if (data instanceof BasicAccountData) {
            return AccountType.BASIC;
        }

        if (data instanceof GoogleAccountData) {
            return AccountType.GOOGLE;
        }

        return AccountType.NONE;
    }

    private static String getServiceName(AccountType accountType) {
        switch (accountType) {
            case BASIC: return "BasicAccountDataService";
            case GOOGLE: return "GoogleAccountDataService";
            default: return "none";
        }
    }

}
