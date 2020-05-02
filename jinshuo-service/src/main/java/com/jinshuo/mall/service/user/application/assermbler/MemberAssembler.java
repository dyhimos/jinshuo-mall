package com.jinshuo.mall.service.user.application.assermbler;

import com.jinshuo.mall.domain.user.model.member.Member;
import com.jinshuo.mall.service.user.application.cmd.MemberCmd;
import com.jinshuo.mall.service.user.application.dto.MemberDto;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;

/**
 * @Classname MemberAssembler
 * @Date 2019/6/17 18:27
 * @Created by dongyh
 */
@Component
public class MemberAssembler {

    /**
     * 返回会员信息
     * @param cmd
     * @return
     */
    public static Member assembleMember(MemberCmd cmd) {
        Member member = new Member();
        BeanUtils.copyProperties(cmd, member);
        return member;
    }


    /**
     * 转化为列表dto
     * @param member
     * @return
     */
    public static MemberDto assembleMemberDto(Member member) throws UnsupportedEncodingException {
        MemberDto memberDto = new MemberDto();
        BeanUtils.copyProperties(member, memberDto);
        memberDto.setId(member.getMemberId().getId());
        //memberDto.setName(URLDecoder.decode(memberDto.getName(), "utf-8"));
        return memberDto;
    }
}
