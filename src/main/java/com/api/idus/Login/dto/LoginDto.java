package com.api.idus.Login.dto;

import com.api.idus.common.dto.ErrorFieldDto;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Getter
@Setter
public class LoginDto {

    @Getter
    @Setter
    public static class Request {
        @NotBlank(message = "별명을 입력해주세요.")
        private String nickname;
        @NotBlank(message = "비밀번호를 입력해주세요.")
        private String password;

    }

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
        private List<ErrorFieldDto> rstList;

    }
}
