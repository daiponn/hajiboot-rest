package com.example.json;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerJson {

    private Integer id;
    @NotNull
    private String firstName;
    @NotNull
    private String lastName;
}
