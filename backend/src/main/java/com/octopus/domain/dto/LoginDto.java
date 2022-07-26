package com.octopus.domain.dto;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter @Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LoginDto {

    @NotBlank
    @Size(min = 6, max = 15)
    private  String userId;

    @NotBlank
    @Size(min = 6, max = 100)
    private  String userPassword;
}
