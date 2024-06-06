package com.example.data.dto.response;

import lombok.*;

@Data
@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ResponseBoardDto {

    private Long pid;

    private String name;

    private String email;

    private String number;

    private String password;

    private String profile;
}
