package com.ekko.secondproject;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Contact {
    private String name;
    private String phoneNumber;
    private String email;
}
