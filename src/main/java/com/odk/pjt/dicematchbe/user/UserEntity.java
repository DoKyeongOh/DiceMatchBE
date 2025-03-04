package com.odk.pjt.dicematchbe.user;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity(name = "user")
public class UserEntity {

    public UserEntity() {

    }

    public UserEntity(User user) {
        this.id = user.id;
        this.nickName = user.nickName;
        this.createdTime = user.createdTime;
        this.updatedTime = user.updatedTime;
    }

    @Id
    @Column(name = "user_id", nullable = false, unique = true)
    @GeneratedValue(strategy = GenerationType.UUID)
    public String id;

    @Column(name = "nickname")
    public String nickName;

    @Column(name = "created_time")
    public long createdTime;

    @Column(name = "updated_time")
    public long updatedTime;

    public User toModel() {
        return new User(id, nickName, createdTime, updatedTime);
    }
}
