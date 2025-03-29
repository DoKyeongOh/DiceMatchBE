package com.odk.pjt.dicematchbe.user;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity(name = "user")
public class User {
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
}
