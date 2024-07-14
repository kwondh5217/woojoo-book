package com.ssafy11.woojoobook.member;

import jakarta.persistence.*;

@Entity
public class Member {
    @Id
    @GeneratedValue
    private Long id;
    private String email;
    private String password;
    private String nickname;
}
