package com.api.sy.Login.service.impl;

import com.api.sy.Login.dto.LoginDto;
import com.api.sy.Login.dto.LogoutDto;
import com.api.sy.Login.service.LoginService;
import com.api.sy.Login.vo.LoginVo;
import com.api.sy.common.exception.DataNotFoundException;
import com.api.sy.common.utility.EncryptUtility;
import com.api.sy.common.utility.ObjectUtility;
import com.api.sy.member.entity.Member;
import com.api.sy.member.repository.MemberRepository;
import com.api.sy.member.repository.specification.MemberSpecification;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class LoginServiceImpl implements LoginService {
    private final MemberRepository memberRepository;


    @Override
    public LoginDto.Response doLogin(LoginDto.Request reqDto, HttpServletRequest request) {
        String encPassword = EncryptUtility.SHA256Encrypt(reqDto.getPassword());

        //유효한 회원인지 조회
        Specification spec = Specification.where(MemberSpecification.equalNickname(reqDto.getNickname()));

        Optional<Member> member = memberRepository.findOne(spec);
        if( member.isEmpty() ) {
            member.orElseThrow(() -> new DataNotFoundException("회원이 존재하지 않습니다."));
        }

        if(!member.get().getPassword().equals(encPassword)) {
            throw new DataNotFoundException("비밀번호를 확인후 다시 로그인 해주세요.");
        }

        LoginVo loginVo = LoginVo.builder().build();
        HttpSession httpSession = request.getSession(true);
        httpSession.setAttribute(ObjectUtility.SESSION_MEMBER, loginVo);

        return null;
    }

    @Override
    public LogoutDto.Response doLogout(HttpServletRequest request) {
        LogoutDto.Response resDto = new LogoutDto.Response();

        HttpSession httpSession = request.getSession(true);
        httpSession.removeAttribute(ObjectUtility.SESSION_MEMBER);

        LogoutDto.RespHeadData headData = LogoutDto.RespHeadData.builder()
                .rst("S")
                .build();
        resDto.setHeadData(headData);

        return resDto;
    }
}