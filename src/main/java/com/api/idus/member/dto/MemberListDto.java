package com.api.idus.member.dto;

import com.api.idus.order.entity.Orders;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;


public class MemberListDto {
    @Getter
    @Setter
    public static class Request {
        private String name;
        private String email;
        
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

    }

    @Getter
    @Setter
    public static class RespBodyData {
        private Long memberId;
        private String name;
        private String nickname;
        private String password;
        private String phone;
        private String email;
        private String gender;

        private Orders orders;
    }

}