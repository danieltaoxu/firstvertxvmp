package com.server;

public enum HttpServerResponseCode {
    BAD_REQUEST(400, "Bad Request"),
    NOT_FOUND(404, "Not Found"),
    OK(200, "OK");

    private HttpServerResponseCode(int aCode, String aName)
    {
        code = aCode;
        name = aName;
    }

    public int getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    private int code;
    private String name;
}
