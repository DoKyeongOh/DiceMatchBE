package com.odk.pjt.dicematchbe.account.basic;

import com.odk.pjt.dicematchbe.account.dto.UpdateAccountRequest;
import com.odk.pjt.dicematchbe.account.dto.BasicAccountDto;
import com.odk.pjt.dicematchbe.exception.BadEntityInputException;
import com.odk.pjt.dicematchbe.exception.DiceMatchException;
import com.odk.pjt.dicematchbe.exception.EntityAlreadyExistException;
import com.odk.pjt.dicematchbe.exception.EntityNotFoundException;
import com.odk.pjt.dicematchbe.util.HashEncryptionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class BasicAccountService {

    private final BasicAccountRepository repository;

    @Autowired
    public BasicAccountService(BasicAccountRepository repository) {
        this.repository = repository;
    }

    public Optional<BasicAccount> getBasicAccount(BasicAccountDto dto) throws DiceMatchException {
        if (dto == null) {
            throw new BadEntityInputException("null");
        }

        if (dto.getIdentity() == null || dto.getIdentity().isEmpty()) {
            throw new BadEntityInputException("identity");
        }

        if (dto.getPassword() == null || dto.getPassword().isEmpty()) {
            throw new BadEntityInputException("password");
        }

        try {
            String passwordHash = HashEncryptionUtil.encrypt("SHA-256", dto.getPassword());
            dto.setPassword(passwordHash);
        } catch (Exception e) {
            throw new DiceMatchException("password hashing fail");
        }

        return repository.findByIdentityAndPassword(dto.getIdentity(), dto.getPassword());
    }

    public BasicAccount register(BasicAccountDto dto) throws DiceMatchException {
        if (getBasicAccount(dto).isPresent()) {
            throw new EntityAlreadyExistException("basic account");
        }

        BasicAccount basicAccount = new BasicAccount();
        basicAccount.accountId = UUID.randomUUID().toString();
        basicAccount.identity = dto.getIdentity();
        basicAccount.password = dto.getPassword();
        return repository.save(basicAccount);
    }

    public BasicAccount updateUserIdMapping(String accountId, String userId) throws DiceMatchException {
        if (accountId == null || accountId.isEmpty()) {
            throw new BadEntityInputException("accountId");
        }

        if (userId == null || userId.isEmpty()) {
            throw new BadEntityInputException("userId");
        }

        BasicAccount basicAccount = repository.findById(accountId).orElseThrow(() ->
                new EntityNotFoundException(""));

        basicAccount.userId = userId;

        return repository.save(basicAccount);
    }

}
