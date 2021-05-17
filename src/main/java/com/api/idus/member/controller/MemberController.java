package com.api.idus.member.controller;

import com.api.idus.common.dto.ErrorFieldDto;
import com.api.idus.common.exception.DataNotFoundException;
import com.api.idus.common.exception.MemberExistException;
import com.api.idus.member.dto.MemberDetailDto;
import com.api.idus.member.dto.MemberJoinDto;
import com.api.idus.member.dto.MemberListDto;
import com.api.idus.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = "/member")
@Slf4j
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;

    @PostMapping("/joinMember")
    @ResponseBody
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
    @ResponseBody
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