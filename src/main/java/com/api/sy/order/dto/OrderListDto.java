package com.api.sy.order.dto;

import com.api.sy.common.dto.ErrorFieldDto;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Getter
@Setter
public class OrderListDto {
    @Getter
    @Setter
    public static class Request {
        @NotBlank(message = "조회할 회원ID가 없습니다.")
        private Long memberId;

    }

    @Getter
    @Setter
    public static class Response {
        private RespHeadData headData;
        private List<RespBodyData> bodyData;

    }

    @Getter
    @Setter
    @Builder
    public static class RespHeadData {
        private String rst;
        private String rstMsg;
        private List<ErrorFieldDto> rstList;
    }

    @Getter
    @Setter
    public static class RespBodyData {
        private Long memberId;
        private String orderNum;
    }

}