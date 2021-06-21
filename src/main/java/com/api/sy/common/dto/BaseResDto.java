package com.api.sy.common.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class BaseResDto {
    private String errorCode;
    private String errorMsg;


}