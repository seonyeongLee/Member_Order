package com.api.idus.Login.controller;

import com.api.idus.Login.dto.LoginDto;
import com.api.idus.Login.dto.LogoutDto;
import com.api.idus.Login.service.LoginService;
import com.api.idus.common.dto.ErrorFieldDto;
import com.api.idus.common.exception.DataNotFoundException;
import com.api.idus.member.dto.MemberDetailDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = "/login")
@Slf4j
@RequiredArgsConstructor
public class LoginController {
    private final LoginService loginService;

    @PostMapping(value = "/doLogin")
    @ResponseBody
    public LoginDto.Response doLogin(@RequestBody LoginDto.Request reqDto,
                                     BindingResult bindingResult,
                                     HttpServletRequest request) {
        LoginDto.Response resDto = new LoginDto.Response();

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
            LoginDto.RespHeadData header = LoginDto.RespHeadData.builder()
                    .rst("F").rstList(list)
                    .build();

            resDto.setHeadData(header);
            return resDto;
        }

        try{
            loginService.doLogin(reqDto, request);

            LoginDto.RespHeadData headData = LoginDto.RespHeadData.builder()
                    .rst("S")
                    .build();

            resDto.setHeadData(headData);
        } catch (DataNotFoundException exception) {
            LoginDto.RespHeadData headData = LoginDto.RespHeadData.builder()
                    .rst("F").rstMsg(exception.getMessage())
                    .build();

            resDto.setHeadData(headData);
        }

        return resDto;
    }

    @PostMapping(value = "/doLogout")
    @ResponseBody
    public LogoutDto.Response doLogout(HttpServletRequest request) {
        return loginService.doLogout(request);
    }

}