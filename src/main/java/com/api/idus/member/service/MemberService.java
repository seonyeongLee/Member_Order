package com.api.idus.member.service;

import com.api.idus.member.dto.MemberDetailDto;
import com.api.idus.member.dto.MemberJoinDto;
import com.api.idus.member.dto.MemberListDto;
import org.springframework.data.domain.Pageable;


public interface MemberService {
    void joinMember(MemberJoinDto.Request reqDto);

    MemberListDto.Response getMemberList(MemberListDto.Request reqDto, Pageable pageable);

    MemberDetailDto.Response getMemberDetail(MemberDetailDto.Request reqDto);

}