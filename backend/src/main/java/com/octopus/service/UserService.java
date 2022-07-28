package com.octopus.service;

import com.octopus.domain.User;
import com.octopus.domain.dto.LoginDto;
import com.octopus.domain.dto.NicknameDto;
import com.octopus.domain.dto.PasswordDto;
import com.octopus.domain.dto.SignUpDto;
import com.octopus.domain.dto.UpdateDto;
import com.octopus.exception.SignUpException;

import com.octopus.exception.UserNotFoundException;
import com.octopus.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor

public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    /* Exception Bean*/
    private final SignUpException signUpException;
    private final UserNotFoundException userNotFoundException;

    /* 도메인 회원가입 */
    @Transactional
    public void signup(SignUpDto signUpDto) {
        if (userRepository.findByUserId(signUpDto.getUserId()).isPresent()) {
            throw signUpException;
        }
        signUpDto.dtoEncodePassword(passwordEncoder.encode(signUpDto.getUserPassword()));
        User user = createUser(signUpDto);
        userRepository.save(user);
    }

    @Transactional(readOnly = true)
    public User getUserWithUserId(String userId) {
        return userRepository.findByUserId(userId).orElseThrow(() -> {
            throw userNotFoundException;
        });
    }



    public String changeUserAvatar(String newAvatar){
        String userId = "jjong04161";
        User user = userRepository.findByUserId(userId)
                .orElseThrow(() -> new UsernameNotFoundException(userId + " -> 데이터베이스에서 찾을 수 없습니다."));
        user.updateAvatar(userId, newAvatar);
        return user.getUserAvatar();

    @Transactional
    public boolean isPasswordEqualDbPassword(LoginDto logindto) {
        User findUser = findUserByUserId(logindto);
        // 입력받은 id, pw조합이 존재한다면 - 삭제
        if (passwordEncoder.matches(logindto.getUserPassword(), findUser.getUserPassword())) {
            userRepository.delete(findUser);
            return true;
        }
        return false;
    }

    @Transactional(readOnly = true)
    protected User findUserByUserId(LoginDto loginDto) {
        return userRepository.findByUserId(loginDto.getUserId()).orElseThrow(() -> {
            throw new UserNotFoundException();
        });

    }

    private User createUser(SignUpDto signUpDto) {
        return User.signUpBuilder()
                .signUpDto(signUpDto)
                .build();
    }

    @Transactional
    public boolean checkUserNickname(NicknameDto nicknameDto) {
        if (getUserInfoByNickname(nicknameDto.getNickname()))
            return false;
        else{
            changeUserNickname(nicknameDto);
            return true;
        }
    }
    @Transactional
    public void changeUserNickname(NicknameDto nicknameDto) {
            getUserWithUserId(nicknameDto.getUserId()).changeNickname(nicknameDto);
    }
    @Transactional
    public boolean changeUserPassword(PasswordDto passwordDto) {
        if (passwordEncoder.matches(passwordDto.getOldPassword(),getUserInfo(passwordDto.getUserId()).getUserPassword())){
            {
                User user = getUserInfo(passwordDto.getUserId());
                user.changePassword(passwordEncoder.encode(passwordDto.getNewPassword()));
                return true;
            }
        }
        else
            return false;
    }

    protected User getUserInfo(String userId){
        return userRepository.findByUserId(userId).orElseThrow(()->{
            throw new UserNotFoundException();
        });
    }

    protected boolean getUserInfoByNickname(String nickname){
        if (userRepository.findByUserNickname(nickname).isPresent()){
            return true;
        }
        else
            return false;
    }




}