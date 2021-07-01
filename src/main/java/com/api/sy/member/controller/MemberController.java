package com.api.sy.member.controller;

import com.api.sy.common.dto.ErrorFieldDto;
import com.api.sy.common.exception.DataNotFoundException;
import com.api.sy.common.exception.MemberExistException;
import com.api.sy.member.dto.MemberDetailDto;
import com.api.sy.member.dto.MemberJoinDto;
import com.api.sy.member.dto.MemberListDto;
import com.api.sy.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RequestMapping(value = "/member")
@RestController
public class MemberController {
    private final MemberService memberService;

    @PostMapping("/joinMember")
    public MemberJoinDto.Response joinMember(@Valid @RequestBody MemberJoinDto.Request reqDto, BindingResult bindingResult) {
        MemberJoinDto.Response resDto = new MemberJoinDto.Response();

        //회원가입 값에 대한 유효성체크
        if (bindingResult.hasErrors()) {
            List<ObjectError> errorList = bindingResult.getAllErrors();

            List<ErrorFieldDto> list = new ArrayList<>();

            ErrorFieldDto errorDto;
            for (ObjectError error : errorList) {
                errorDto = ErrorFieldDto.builder()
                        .field(((FieldError)error).getField())
                        .reason(error.getDefaultMessage())
                        .build();
                list.add(errorDto);
            }
            MemberJoinDto.RespHeadData header = MemberJoinDto.RespHeadData.builder()
                    .rst("F").rstList(list)
                    .build();

            resDto.setHeadData(header);
            return resDto;
        }

        try {
            memberService.joinMember(reqDto);
        } catch(MemberExistException | SecurityException  exception) {
            MemberJoinDto.RespHeadData header = MemberJoinDto.RespHeadData.builder()
                    .rst("F").rstMsg(exception.getMessage())
                    .build();
            resDto.setHeadData(header);
            return resDto;
        }

        MemberJoinDto.RespHeadData header = MemberJoinDto.RespHeadData.builder()
                .rst("S").build();
        resDto.setHeadData(header);

        return resDto;
    }

    @PostMapping(value = "/getMemberList")
    @ResponseBody
    public MemberListDto.Response getMemberList(@RequestBody MemberListDto.Request reqDto,
                                                @PageableDefault Pageable pageable) {

        return memberService.getMemberList(reqDto, pageable);
    }

    @PostMapping(value = "/getMemberDetail")
    public MemberDetailDto.Response getMemberDetail(@RequestBody MemberDetailDto.Request reqDto,
                                                    BindingResult bindingResult) {
        MemberDetailDto.Response resDto = new MemberDetailDto.Response();

        if (bindingResult.hasErrors()) {
            List<ObjectError> errorList = bindingResult.getAllErrors();

            List<ErrorFieldDto> list = new ArrayList<>();

            ErrorFieldDto errorDto;
            for (ObjectError error : errorList) {
                errorDto = ErrorFieldDto.builder()
                        .field(((FieldError)error).getField())
                        .reason(error.getDefaultMessage())
                        .build();
                list.add(errorDto);
            }
            MemberDetailDto.RespHeadData header = MemberDetailDto.RespHeadData.builder()
                    .rst("F").rstList(list)
                    .build();

            resDto.setHeadData(header);
            return resDto;
        }


        try {
            resDto = memberService.getMemberDetail(reqDto);
        } catch (DataNotFoundException exception) {
            resDto = new MemberDetailDto.Response();

            resDto.setHeadData(MemberDetailDto.RespHeadData.builder()
                    .rst("F").rstMsg(exception.getMessage())
                    .build());
        }

        MemberDetailDto.RespHeadData header = MemberDetailDto.RespHeadData.builder()
                .rst("S").build();
        resDto.setHeadData(header);

        return resDto;
    }


}