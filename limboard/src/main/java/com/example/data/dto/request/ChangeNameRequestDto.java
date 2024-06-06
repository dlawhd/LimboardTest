package com.example.data.dto.request;

import lombok.*;

@ToString
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ChangeNameRequestDto {

    private String email;
    private String name;
}