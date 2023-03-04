package com.chtw.response;

import lombok.Data;

@Data
public class CommRespUtils<T> {
    private int code;
    private String message;
    private T data;

    private CommRespUtils(int code, String message) {
        this.code = code;
        this.message = message;
        this.data = null;
    }

    private CommRespUtils(T data) {
        this.code = CodeAndMsg.SUCCESS.getCode();
        this.message = CodeAndMsg.SUCCESS.getMessage();
        this.data = data;
    }

    public static <T> CommRespUtils<T> success(T data) {
        return new CommRespUtils<>(data);
    }

    public static <T> CommRespUtils<T> fail(String msg) {
        return new CommRespUtils<>(CodeAndMsg.FAIL.getCode(), msg);
    }

    public static<T> CommRespUtils<T> authExpired(){
        return new CommRespUtils<>(-1, "授权过期！");
    }
}
