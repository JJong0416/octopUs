package com.octopus.mail;

import com.octopus.dto.request.EmailVerificationReq;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class EmailController {

    private final EmailTokenService emailTokenService;

    // 유저가 입력한 이메일로 메일 보내기
    @PostMapping("/email")
    public ResponseEntity<Boolean> postEmailVerifiedCode(@RequestBody EmailVerificationReq emailVerificationReq){
        return new ResponseEntity<>(
                emailTokenService.createEmailToken(emailVerificationReq.getUserEmail()), HttpStatus.OK
        );
    }

    // 유저가 입력한 코드가 이메일로 보낸 코드와 같은지 확인
    @PostMapping("/email/check")
    public ResponseEntity<Boolean> verifiedEmailCode(@RequestBody EmailVerificationReq emailVerificationReq){
        return new ResponseEntity<>(
                emailTokenService.findByEmailCodeAndExpirationDateAfterAndExpired(emailVerificationReq.getEmailCode()), HttpStatus.OK
        );
    }
}
