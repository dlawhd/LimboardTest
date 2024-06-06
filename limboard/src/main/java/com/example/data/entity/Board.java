package com.example.data.entity;

import lombok.*;


import javax.persistence.*;
import java.time.LocalDateTime;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long pid;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false, unique = true)
    private String number;

    @Column(nullable = false, unique = true)
    private String password;

    @Column
    private String profile;

    @Column
    private LocalDateTime createAt;

    @Column
    private LocalDateTime updatedAt;

    @OneToOne(mappedBy = "board")
    @ToString.Exclude
    private BoardDetail boardDetail;

    @ManyToOne
    @JoinColumn(name = "hobby_id")
    @ToString.Exclude
    private Hobby hobby;
}