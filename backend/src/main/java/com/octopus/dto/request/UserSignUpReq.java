package com.octopus.dto.request;

import com.octopus.domain.type.PlatformType;
import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.*;

@Getter
public class UserSignUpReq {

    @Pattern(regexp="[a-z0-9]{6,15}")
    private final String userId;

    @NotBlank
    @Size(min = 6, max = 100)
    private String userPassword;

    @Email
    private final String userEmail;

    @NotBlank
    @Size(min = 4, max = 10)
    private final String userNickname;

    @NotEmpty
    private final String userAvatar;

    private final Integer userPoint = 1000;

    private final PlatformType platformType = PlatformType.DOMAIN;

    public void dtoEncodePassword(String encodePassword){
        this.userPassword = encodePassword;
    }

    @Builder
    public UserSignUpReq(
            String userId, String userPassword, String userEmail, String userNickname, String userAvatar
    ) {
        this.userId = userId;
        this.userPassword = userPassword;
        this.userEmail = userEmail;
        this.userNickname = userNickname;
        StringBuilder sb = new StringBuilder();
        sb.append(userAvatar).append(", ")
                .append(0).append(", ")
                .append(0).append(", ")
                .append(0);
        this.userAvatar = sb.toString();
    }

}
