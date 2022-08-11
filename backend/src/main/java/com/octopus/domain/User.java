package com.octopus.domain;

import com.octopus.dto.request.UserSignUpReq;
import com.octopus.domain.type.PlatformType;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@ToString
@Table(name = "user")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_no", unique = true)
    private Long userNo;

    @Column(name = "user_id", length = 15, nullable = false, unique = true)
    private String userId;

    @Column(name = "user_nickname", length = 10, nullable = false, unique = true)
    private String userNickname;

    @Column(name = "user_password", length = 100, nullable = false)
    private String userPassword;

    @Column(name = "user_email", length = 30, nullable = false, unique = true)
    private String userEmail;

    @Column(name = "user_point")
    private Integer userPoint;

    @Column(name = "user_avatar", nullable = false)
    private String userAvatar;

    @Column(name = "platform_type")
    private PlatformType platformType;

    @Column(name = "platform_access_token")
    private Long platformAccessToken;

    @OneToMany(mappedBy = "user")
    private final Set<Octopus> octopus = new HashSet<>();

    @Builder
    public User(String userId, String userPassword, String userNickname, String userEmail,
                String userAvatar, Integer userPoint, PlatformType platformType) {
        this.userId = userId;
        this.userPassword = userPassword;
        this.userNickname = userNickname;
        this.userEmail = userEmail;
        this.userAvatar = userAvatar;
        this.userPoint = userPoint;
        this.platformType = platformType;
    }

    public void updateAvatar(String avatar){
        this.userAvatar = avatar;
    }

    public void updatePassword(final String newPassword){
        this.userPassword = newPassword;
    }

    public void changeNickname(String newNickname) {
        this.userNickname = newNickname;
    }

    public void updatePoint(Integer newUserPoint) {this.userPoint = newUserPoint;}
}
