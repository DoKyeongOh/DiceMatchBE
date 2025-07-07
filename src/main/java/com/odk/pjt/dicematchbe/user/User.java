package com.odk.pjt.dicematchbe.user;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity(name = "user")
@Getter @Setter
public class User {
    @Id
    @Column(name = "user_id", nullable = false, unique = true)
    private String userId;

    @Column(name = "nickname")
    private String nickName;

    @Column(name = "created_time")
    private long createdTime;

    @Column(name = "updated_time")
    private long updatedTime;
}
