package com.odk.pjt.dicematchbe.account.data;

import com.odk.pjt.dicematchbe.exception.BadEntityInputException;
import com.odk.pjt.dicematchbe.exception.DiceMatchException;
import com.odk.pjt.dicematchbe.exception.EntityAlreadyExistException;

public interface AccountDataService<T extends AccountData> {
    T register(T accountData) throws DiceMatchException;

    T update(T accountData) throws DiceMatchException;

    String delete(String accountId);
}
