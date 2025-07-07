package com.odk.pjt.dicematchbe.account.basic;

import com.odk.pjt.dicematchbe.account.Account;
import com.odk.pjt.dicematchbe.account.AccountRepository;
import com.odk.pjt.dicematchbe.account.AccountType;
import com.odk.pjt.dicematchbe.account.dto.BasicAccountDTO;
import com.odk.pjt.dicematchbe.user.User;
import com.odk.pjt.dicematchbe.user.UserRepository;
import com.odk.pjt.dicematchbe.util.HashEncryptionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.NoSuchAlgorithmException;
import java.util.Optional;
import java.util.UUID;

@Service
public class BasicAccountService {

    private final AccountRepository accountRepository;
    private final BasicAccountInfoRepository infoRepository;
    private final UserRepository userRepository;

    @Autowired
    public BasicAccountService(AccountRepository accountRepository, BasicAccountInfoRepository infoRepository,
                               UserRepository userRepository) {
        this.accountRepository = accountRepository;
        this.infoRepository = infoRepository;
        this.userRepository = userRepository;
    }

    public Optional<Account> getAccount(BasicAccountDTO dto) throws NoSuchAlgorithmException {
        Optional<BasicAccountInfo> optionalInfo = infoRepository.findByIdentityAndPasswordHash(dto.getIdentity(),
                HashEncryptionUtil.encrypt("SHA-256", dto.getPassword()));

        return optionalInfo.flatMap(basicAccountInfo -> accountRepository.findById(basicAccountInfo.getAccountId()));
    }

    @Transactional
    public Account register(BasicAccountDTO dto) throws NoSuchAlgorithmException {
        if (infoRepository.findByIdentity(dto.getIdentity()).isPresent()) {
            throw new RuntimeException("Account already exists");
        }

        String accountId = UUID.randomUUID().toString();
        String userId = UUID.randomUUID().toString();
        long now = System.currentTimeMillis();

        BasicAccountInfo info = new BasicAccountInfo();
        info.setAccountId(accountId);
        info.setIdentity(dto.getIdentity());
        info.setPasswordHash(HashEncryptionUtil.encrypt("SHA-256", dto.getPassword()));
        infoRepository.save(info);

        User user = new User();
        user.setUserId(userId);
        user.setNickName("새로운 유저");
        user.setCreatedTime(now);
        user.setUpdatedTime(now);
        userRepository.save(user);

        Account account = new Account();
        account.setAccountId(accountId);
        account.setType(AccountType.BASIC);
        account.setUserId(userId);
        account.setCreatedTime(now);
        account.setActive(true);
        return accountRepository.save(account);
    }

}
