package com.octopus.domain;

import com.octopus.domain.dto.SignUpDto;
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

    @Enumerated(EnumType.STRING)
    @Column(name = "platform_type")
    private PlatformType platformType;

    @Column(name = "platform_access_token")
    private Long platformAccessToken;

    @ManyToMany
    @JoinTable(
            name = "octopus_table",
            joinColumns = @JoinColumn(name = "user_no"),
            inverseJoinColumns = @JoinColumn(name = "mission_no")
    )
    private final Set<Mission> missions = new HashSet<>();

    @Builder(builderMethodName = "signUpBuilder")
    public User(SignUpDto signUpDto) {
        this.userId = signUpDto.getUserId();
        this.userPassword = signUpDto.getUserPassword();
        this.userNickname = signUpDto.getUserNickname();
        this.userEmail = signUpDto.getUserEmail();
        this.userAvatar = signUpDto.getUserAvatar();
        this.userPoint = signUpDto.getUserPoint();
    }
}
