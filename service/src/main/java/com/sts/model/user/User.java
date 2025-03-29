package com.sts.model.user;

import com.sts.util.enums.UserStatus;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class User{

    private Long id;

    private String phoneNumber;

    private String email;

    private String fullName;

    private String password;

    private UserStatus status;

}
