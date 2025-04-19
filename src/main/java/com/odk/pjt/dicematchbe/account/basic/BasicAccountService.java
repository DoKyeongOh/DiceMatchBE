package com.odk.pjt.dicematchbe.account.basic;

import com.odk.pjt.dicematchbe.account.dto.AccountUserIdUpdateRequest;
import com.odk.pjt.dicematchbe.account.dto.BasicAccountDTO;
import com.odk.pjt.dicematchbe.exception.BadEntityInputException;
import com.odk.pjt.dicematchbe.exception.DiceMatchException;
import com.odk.pjt.dicematchbe.exception.EntityAlreadyExistException;
import com.odk.pjt.dicematchbe.exception.EntityNotFoundException;
import com.odk.pjt.dicematchbe.util.HashEncryptionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BasicAccountService {

    private final BasicAccountRepository repository;

    @Autowired
    public BasicAccountService(BasicAccountRepository repository) {
        this.repository = repository;
    }

    public Optional<BasicAccount> getBasicAccount(BasicAccountDTO dto) throws DiceMatchException {
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

    public BasicAccount register(BasicAccountDTO dto) throws DiceMatchException {
        if (getBasicAccount(dto).isPresent()) {
            throw new EntityAlreadyExistException("basic account");
        }

        try {
            String passwordHash = HashEncryptionUtil.encrypt("SHA-256", dto.getPassword());
            dto.setPassword(passwordHash);
        } catch (Exception e) {
            throw new DiceMatchException("password hashing fail");
        }

        BasicAccount basicAccount = new BasicAccount();
        basicAccount.setIdentity(dto.getIdentity());
        basicAccount.setPassword(dto.getPassword());
        return repository.save(basicAccount);
    }

    public BasicAccount updateUserIdMapping(AccountUserIdUpdateRequest request) throws DiceMatchException {
        if (request.getAccountId() == null || request.getAccountId().isEmpty()) {
            throw new BadEntityInputException("accountId");
        }

        if (request.getUserId() == null || request.getUserId().isEmpty()) {
            throw new BadEntityInputException("userId");
        }

        BasicAccount basicAccount = repository.findById(request.getAccountId()).orElseThrow(() ->
                new EntityNotFoundException(""));

        basicAccount.setUserId(request.getUserId());

        return repository.save(basicAccount);
    }

}
