package com.api.sy.common.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class ErrorResDto {
    private String rst;
    private String rstMsg;
    private List<ErrorFieldDto> rstList;

}