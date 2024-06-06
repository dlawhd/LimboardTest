package com.example.data.dto.request;

import lombok.*;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class RequestBoardDto {

    private String name;

    private String number;

    private String password;

    private String email;

    private String profile;
}