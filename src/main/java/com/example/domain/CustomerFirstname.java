package com.example.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Value;

//DTO変換お試し用のクラス

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerFirstname {
    private String firstName;
}
