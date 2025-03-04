package com.odk.pjt.dicematchbe.user;

public class User {
    public String id;
    public String nickName;
    public long createdTime;
    public long updatedTime;

    public User() {
    }

    public User(String id, String nickName, long createdTime, long updatedTime) {
        this.id = id;
        this.nickName = nickName;
        this.createdTime = createdTime;
        this.updatedTime = updatedTime;
    }
}
