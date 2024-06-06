package com.example.data.dto.SignDto;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class SignUpDto {
    private String email;
    private String password;
    private String name;
    private String number;
}
