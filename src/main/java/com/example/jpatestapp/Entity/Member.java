package com.example.jpatestapp.Entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "member")
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;

    @Column(nullable = false, unique = true)
    private String memberName;
    @Column(nullable = false, unique = true)
    private String memberId;
    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String registerDate;

    @Column(nullable = true)
    private String lastLoginDate;

    @Column(nullable = false)
    private String authType;    //general, super

}