package com.example.jpatestapp.Entity;


import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "board")
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idx;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String texts;

    @Column(nullable = false)
    private String writer;

    @Column(nullable = false)
    private String phoneNumber;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String contentCode;

    @Column(nullable = false)
    private String fileName;

    @Column(nullable = false)
    private String registerDate;

    @Column(nullable = false)
    private String updateDate;

    @Column(nullable = false)
    private String deleteYn = "N";

    @Column(nullable = false)
    private String status = "W";

}
