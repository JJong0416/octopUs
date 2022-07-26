package com.octopus.domain.dto;

import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter @Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SignUpDto {

    @NotBlank
    @Size(min = 6, max = 15)
    private String userId;

    @NotBlank
    @Size(min = 6, max = 100)
    private String userPassword;

    @Email
    private String userEmail;

    @NotBlank
    @Size(min = 4, max = 10)
    private String userNickname;

    private String userAvatar;
}
