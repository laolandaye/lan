package com.sxsh.note;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SignVerifyDTO implements Serializable{

    private String timestamp;

    private String data;

    private String sign;


}
