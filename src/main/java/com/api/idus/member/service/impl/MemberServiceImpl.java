package com.api.idus.member.service.impl;

import com.api.idus.common.exception.DataNotFoundException;
import com.api.idus.common.exception.MemberExistException;
import com.api.idus.common.utility.EncryptUtility;
import com.api.idus.common.utility.MapperUtility;
import com.api.idus.common.utility.ObjectUtility;
import com.api.idus.member.dto.MemberDetailDto;
import com.api.idus.member.dto.MemberJoinDto;
import com.api.idus.member.dto.MemberListDto;
import com.api.idus.member.entity.Member;
import com.api.idus.member.repository.MemberRepository;
import com.api.idus.member.repository.specification.MemberSpecification;
import com.api.idus.member.service.MemberService;
import com.api.idus.order.entity.Orders;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {
    private final MemberRepository memberRepository;

    @Override
    public void joinMember(MemberJoinDto.Request reqDto) {
        reqDto.setPassword(EncryptUtility.SHA256Encrypt(reqDto.getPassword()));

        //유효한 회원인지 조회
        Specification spec = Specification.where(MemberSpecification.equalNickname(reqDto.getNickname()));

        Optional<Member> nickNameChk = memberRepository.findOne(spec);
        if( nickNameChk.isPresent() ) {
            nickNameChk.orElseThrow(() -> new MemberExistException("사용중인 별명입니다. 다른 별명을 입력해주세요."));
        }

        Member member = new ModelMapper().map(reqDto, Member.class);
        memberRepository.save(member);

    }

    @Override
    public MemberListDto.Response getMemberList(MemberListDto.Request reqDto, Pageable pageable) {
        MemberListDto.Response resDto = new MemberListDto.Response();

        int page = (pageable.getPageNumber() == 0) ? 0 : (pageable.getPageNumber() - 1);
        pageable = PageRequest.of(page, pageable.getPageSize());

        Specification spec = null;
        if(ObjectUtility.isNotEmpty(reqDto.getName())) {
            spec = Specification.where(MemberSpecification.equalName(reqDto.getName()));
        }

        if(ObjectUtility.isNotEmpty(reqDto.getEmail())) {
            if(spec == null) {
                spec = Specification.where(MemberSpecification.equalEmail(reqDto.getEmail()));
            } else {
                spec = spec.and(MemberSpecification.equalEmail(reqDto.getEmail()));
            }
        }

        Page<Member> list = memberRepository.findAll(spec, pageable);

        List<MemberListDto.RespBodyData> bodyList = new ArrayList<>();
        MemberListDto.RespBodyData bodyData;
        for(Member member : list) {
            bodyData = MapperUtility.convertData(member, MemberListDto.RespBodyData.class);
            if(ObjectUtility.isNotEmpty(member.getOrders())) 
                bodyData.setOrders(MapperUtility.convertData(member.getOrders().get(0), Orders.class));
            
            bodyList.add(bodyData);
        }

        resDto.setBodyData(bodyList);

        MemberListDto.RespHeadData header = MemberListDto.RespHeadData.builder()
                .rst("S").build();
        resDto.setHeadData(header);

        return resDto;
    }

    @Override
    public MemberDetailDto.Response getMemberDetail(MemberDetailDto.Request reqDto) {
        Optional<Member> memberInfo = memberRepository.findById(reqDto.getMemberId());
        if( memberInfo.isEmpty() ) {
            memberInfo.orElseThrow(() -> new DataNotFoundException("회원이 존재하지 않습니다."));
        }


        MemberDetailDto.RespBodyData bodyData = MapperUtility.convertData(memberInfo.get(), MemberDetailDto.RespBodyData.class);

        MemberDetailDto.Response resDto = new MemberDetailDto.Response();
        resDto.setBodyData(bodyData);

        return resDto;
    }

}