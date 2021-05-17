package com.api.idus.member.dto;

import com.api.idus.common.annotataion.Gender;
import com.api.idus.common.dto.ErrorFieldDto;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.List;

public class MemberJoinDto {
    @Getter
    @Setter
    public static class Request {
        @NotBlank(message = "이름을 입력해주세요.")
        @Size(max = 20, message = "이름은 20자 이하로 입력해주세요.")
        @Pattern(regexp="^[가-힣a-zA-Z]*$", message = "이름은 한글, 영문 대소문자만 입력해주세요.")
        private String name;

        @NotBlank(message = "별명을 입력해주세요.")
        @Size(max = 30, message = "별명은 30자 이하로 입력해주세요.")
        @Pattern(regexp="^[a-z]*$", message = "별명은 영문 소문자만 입력해주세요.")
        private String nickname;

        @NotBlank(message = "비밀번호을 입력해주세요.")
        @Size(min = 10, max = 16, message = "비밀번호는 10~16자 사이로 입력해주세요.")
        @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[$@$!%*?&])[A-Za-z\\d$@$!%*?&]{10,16}",
                    message = "비밀범호는 영문 대문자, 영문 소문자, 특수 문자, 숫자 각 1개 이상씩 포함해서 입력해주세요.")
        private String password;

        @NotBlank(message = "전화번호을 입력해주세요.")
        @Size(max = 20, message = "전화번호은 20자 이하로 입력해주세요.")
        @Pattern(regexp="^[0-9]*$", message = "전화번호는 숫자만 입력해주세요.")
        @Pattern(regexp="^\\d{3}\\d{3,4}\\d{4}$", message = "올바르지 않은 전화번호 입니다.")
        private String phone;

        @NotBlank(message = "이메일을 입력해주세요.")
        @Size(max = 100, message = "이메일은 100자 이하로 입력해주세요.")
        @Email(message = "올바르지 않은 이메일 입니다.")
        private String email;

        @NotBlank(message = "성별을 입력해주세요.")
        @Gender
        private String gender;
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
