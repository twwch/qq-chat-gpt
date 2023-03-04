package com.chtw.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum CodeAndMsg {
    SUCCESS(0, "成功"),
    FAIL(2, "失败");
    private int code;
    private String message;
}