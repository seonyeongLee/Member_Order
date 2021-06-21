package com.api.sy.Login.service;

import com.api.sy.Login.dto.LoginDto;
import com.api.sy.Login.dto.LogoutDto;

import javax.servlet.http.HttpServletRequest;

public interface LoginService {
    LoginDto.Response doLogin(LoginDto.Request reqDto, HttpServletRequest request);

    LogoutDto.Response doLogout(HttpServletRequest request);

}