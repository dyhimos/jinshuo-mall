package com.jinshuo.mall.service.user.application.assermbler;

import com.jinshuo.mall.domain.user.model.member.Member;
import com.jinshuo.mall.service.user.application.dto.UserMemberDto;
import lombok.Data;
import org.springframework.beans.BeanUtils;

/**
 * Created by 19458 on 2019/9/2.
 */
@Data
public class UserMemberAssembler {

    /**
     * 转化用户信息
     *
     * @param member
     * @return
     */
    public static UserMemberDto assembleUserAccount(Member member) {
        if (null == member) {
            return null;
        }
        UserMemberDto userMemberDto = new UserMemberDto();
        BeanUtils.copyProperties(member, userMemberDto);
        userMemberDto.setId(member.getUserAccountId().getId().toString());
        if (null != member.getSex()) {
            userMemberDto.setSex(member.getSex().getCode());
        }
        return userMemberDto;
    }
}
