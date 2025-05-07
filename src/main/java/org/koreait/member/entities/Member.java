package org.koreait.member.entities;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Member {
    private long seq;
    private String email;
    private String password;
    private String name;
    private String mobile;
    private boolean terms;
    private boolean isAdmin;
    private LocalDateTime regDt;
}
