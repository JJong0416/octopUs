package com.octopus.jwt.dto;

import lombok.*;

@Getter @Setter @Builder
@AllArgsConstructor @NoArgsConstructor
public class JwtTokenDto {
    private String token;
}
