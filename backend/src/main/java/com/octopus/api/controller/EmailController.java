package com.octopus.api.controller;

import com.octopus.dto.request.EmailVerificationReq;
import com.octopus.api.service.EmailTokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class EmailController {

    private final EmailTokenService emailTokenService;

    // 유저가 입력한 이메일로 메일 보내기
    @PostMapping("/email")
    public ResponseEntity<String> postEmailVerifiedCode(@RequestBody EmailVerificationReq emailVerificationReq) {
        String msg = "이메일오류";
        msg = emailTokenService.createEmailToken(emailVerificationReq.getUserEmail());
        return new ResponseEntity<>(msg , HttpStatus.OK);
    }

    // 유저가 입력한 코드가 이메일로 보낸 코드와 같은지 확인
    @PostMapping("/email/check")
    public ResponseEntity<Boolean> verifiedEmailCode(@RequestBody EmailVerificationReq emailVerificationReq) {
        return new ResponseEntity<>(
                emailTokenService.findEmailToken(emailVerificationReq.getEmailCode()), HttpStatus.OK);
    }
}
