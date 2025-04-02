package com.odk.pjt.dicematchbe.account.data.basic;

import com.odk.pjt.dicematchbe.account.data.AccountDataService;
import com.odk.pjt.dicematchbe.exception.BadEntityInputException;
import com.odk.pjt.dicematchbe.exception.DiceMatchException;
import com.odk.pjt.dicematchbe.exception.EntityAlreadyExistException;
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
    public BasicAccountData register(BasicAccountData accountData) throws DiceMatchException {
        validate(accountData);

        try {
            accountData.password = HashEncryptionUtil.encrypt("SHA-256", accountData.password);
        } catch (Exception e) {
            throw new DiceMatchException("register fail: password hashing fail");
        }

        if (repository.findByIdentityAndPwHash(accountData.identity, accountData.password).isPresent()) {
            throw new EntityAlreadyExistException("register fail: account data already exist");
        }

        return repository.save(accountData);
    }

    @Override
    public BasicAccountData update(BasicAccountData accountData) throws DiceMatchException {
        validate(accountData);

        try {
            accountData.password = HashEncryptionUtil.encrypt("SHA-256", accountData.password);
        } catch (Exception e) {
            throw new DiceMatchException("update fail: password hashing fail");
        }

        return repository.save(accountData);
    }

    private void validate(BasicAccountData accountData) throws BadEntityInputException {
        if (accountData.identity == null || accountData.identity.isEmpty()) {
            throw new BadEntityInputException("register fail: identity - " + accountData.identity);
        }

        if (accountData.password == null || accountData.password.isEmpty()) {
            throw new BadEntityInputException("register fail: password - " + accountData.password);
        }
    }

    @Override
    public String delete(String accountId) {
        return "";
    }
}
