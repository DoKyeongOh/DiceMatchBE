package com.odk.pjt.dicematchbe.account.data;

public interface AccountDataService<T extends AccountData> {
    T register(T data);

    T update(T data);

    String delete(String accountId);
}
