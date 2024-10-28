package com.cofjus.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorCode {

    public final static  ErrorCode SUCCESS = new ErrorCode(0, "success");

    private int code;

    private String msg;

}
