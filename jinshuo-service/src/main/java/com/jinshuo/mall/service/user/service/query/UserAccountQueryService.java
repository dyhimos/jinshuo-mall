package com.jinshuo.mall.service.user.service.query;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jinshuo.core.exception.user.UcBizException;
import com.jinshuo.core.exception.user.UcReturnCode;
import com.jinshuo.mall.domain.user.model.member.Member;
import com.jinshuo.mall.domain.user.model.userAccount.UserAccount;
import com.jinshuo.mall.domain.user.model.userAccount.UserAccountId;
import com.jinshuo.mall.service.user.application.dto.MemberDto;
import com.jinshuo.mall.service.user.application.dto.UserAccountDetailDto;
import com.jinshuo.mall.service.user.application.dto.UserManagerAccountDto;
import com.jinshuo.mall.service.user.application.qry.UserAccountQry;
import com.jinshuo.mall.service.user.mybatis.MemberRepo;
import com.jinshuo.mall.service.user.mybatis.RoleRepo;
import com.jinshuo.mall.service.user.mybatis.UserAccountRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Administrator on 2020/2/12.
 * 用户信息查询相关接口
 */
@Service
public class UserAccountQueryService {

    @Autowired
    private UserAccountRepo userAccountRepo;

    @Autowired
    private MemberRepo memberRepo;

    @Autowired
    private RoleRepo roleRepo;

    /**
     * 查询客户信息
     *
     * @param userId
     * @return
     */
    public Member queryByUserId(Long userId) {
        return memberRepo.queryByUserId(userId);
    }

    /**
     * 查询用户详情
     *
     * @param userAccountId
     * @return
     */
    public UserAccountDetailDto queryManagerUserDetail(UserAccountId userAccountId) {
        Long userId = userAccountId.getId();
        Long shopId = 10066L;
        //判断用户是否存在
        UserAccount userAccount = userAccountRepo.findById(userAccountId);
        if (userAccount == null) {
            throw new UcBizException(UcReturnCode.UC200021.getMsg(), UcReturnCode.UC200021.getCode());
        }

        //获取已设置的角色列表
        List<Long> roleIds = roleRepo.getUserRoleIdList(userId);
        List<String> roleIdsStr = roleIds.stream().map(t -> String.valueOf(t)).collect(Collectors.toList());
        //获取已设置的菜单列表
        List<Long> menuIds = roleRepo.getUserMenuIdList(userId, shopId);
        List<String> menuIdsStr = menuIds.stream().map(t -> String.valueOf(t)).collect(Collectors.toList());
        UserAccountDetailDto userAccountDetailDto = UserAccountDetailDto.builder()
                .id(userId)
                .username(userAccount.getUsername())
                .nickname(userAccount.getNickname())
                .phone(userAccount.getPhone())
                .roleIds(roleIdsStr)
                .menuIds(menuIdsStr)
                .build();
        return userAccountDetailDto;
    }


    /**
     * 查询后台用户列表
     *
     * @param query
     * @return
     */
    public PageInfo<MemberDto> queryManagerUser(UserAccountQry query) {
        PageHelper.startPage(query.getPageNum(), query.getPageSize());
        List<UserManagerAccountDto> managerAccountDtoList = userAccountRepo.queryManagerAccount(query);
        PageInfo pageInfo = new PageInfo<>(managerAccountDtoList);
        return pageInfo;
    }

}
