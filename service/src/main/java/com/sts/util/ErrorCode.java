package com.sts.util;

import lombok.Getter;

@Getter
public enum ErrorCode{

    PHONE_NUMBER_ALREADY_EXISTS(409, "Phone number already exists"),
    OTP_INVALID(422, "OTP is invalid");

    private final int code;
    private final String message;

    ErrorCode(int code, String message){
        this.code = code;
        this.message = message;
    }

}
