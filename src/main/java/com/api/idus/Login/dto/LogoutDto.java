package com.api.idus.Login.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LogoutDto {

    @Getter
    @Setter
    public static class Response {
        private RespHeadData headData;

    }

    @Getter
    @Setter
    @Builder
    public static class RespHeadData {
        private String rst;
        private String rstMsg;

    }
}
