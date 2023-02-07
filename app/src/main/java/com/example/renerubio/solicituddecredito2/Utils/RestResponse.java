package com.example.renerubio.solicituddecredito2.Utils;

public class RestResponse {
    byte[] content;
    int httpStatus;

    public RestResponse(int httpStatus, byte[] content) {
        this.content = content;
        this.httpStatus = httpStatus;
    }

    public byte[] getContent() {
        return content;
    }

    public int getHttpStatus() {
        return httpStatus;
    }
}


