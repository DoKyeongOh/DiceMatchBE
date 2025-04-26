package com.odk.pjt.dicematchbe.user;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    public String userId;
    public String nickName;

    public static UserDto from(User user) {
        if (user == null) {
            return null;
        }

        UserDto dto = new UserDto();
        dto.userId = user.id;
        dto.nickName = user.nickName;
        return dto;
    }
}
