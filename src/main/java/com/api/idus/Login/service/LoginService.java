package com.api.idus.Login.service;

import com.api.idus.Login.dto.LoginDto;
import com.api.idus.Login.dto.LogoutDto;

import javax.servlet.http.HttpServletRequest;

public interface LoginService {
    LoginDto.Response doLogin(LoginDto.Request reqDto, HttpServletRequest request);

    LogoutDto.Response doLogout(HttpServletRequest request);

}