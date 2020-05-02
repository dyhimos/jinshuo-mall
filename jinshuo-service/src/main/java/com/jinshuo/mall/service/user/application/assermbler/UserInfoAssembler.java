package com.jinshuo.mall.service.user.application.assermbler;

import com.jinshuo.mall.domain.user.model.member.Member;
import com.jinshuo.mall.domain.user.model.userAccountPlatform.UserAccountPlatform;
import com.jinshuo.mall.service.user.application.dto.MemberInfoDto;
import com.jinshuo.mall.service.user.application.dto.UserAccountDto;
import com.jinshuo.mall.service.user.application.dto.UserInfoDto;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

/**
 * @Description: 用户信息转化类
 * @Author: dongyh
 * @CreateDate: 2019/8/31 16:01
 * @UpdateUser: dongyh
 * @UpdateDate: 2019/8/31 16:01
 * @UpdateRemark:
 * @Version: 1.0
 */
@Component
public class UserInfoAssembler {

    /**
     * 返回地址dto
     *
     * @param userAccountPlatform
     * @return
     */
    public static UserInfoDto assembleUserinfoDto(UserAccountPlatform userAccountPlatform) throws UnsupportedEncodingException {
        UserInfoDto userInfoDto = new UserInfoDto();
        BeanUtils.copyProperties(userAccountPlatform, userInfoDto);
        userInfoDto.setNickname(URLDecoder.decode(userInfoDto.getNickname(), "utf-8"));

        UserAccountDto userAccountDto = new UserAccountDto();
        BeanUtils.copyProperties(userAccountPlatform.getUserAccount(), userAccountDto);
        userInfoDto.setUserAccount(userAccountDto);

        MemberInfoDto memberInfoDto = new MemberInfoDto();
        BeanUtils.copyProperties(userAccountPlatform.getMember(), memberInfoDto);
        userInfoDto.setMember(memberInfoDto);

        if (memberInfoDto.getPid() != null) {
            memberInfoDto.setParentName(userAccountPlatform.getMember().getParentMember().getSurname());
        }
        return userInfoDto;
    }

    /**
     * 返回地址dto
     *
     * @param member
     * @return
     */
    public static UserInfoDto assembleMemberInfoDto(Member member) throws UnsupportedEncodingException {
        UserInfoDto userInfoDto = new UserInfoDto();
        BeanUtils.copyProperties(member, userInfoDto);
        return userInfoDto;
    }
}
