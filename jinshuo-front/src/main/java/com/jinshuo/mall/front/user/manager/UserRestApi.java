package com.jinshuo.mall.front.user.manager;

import com.github.pagehelper.PageInfo;
import com.jinshuo.core.response.WrapperResponse;
import com.jinshuo.mall.domain.user.model.userAccount.UserAccountId;
import com.jinshuo.mall.service.user.application.cmd.LoginPasswordCmd;
import com.jinshuo.mall.service.user.application.cmd.UserManagerAccountCmd;
import com.jinshuo.mall.service.user.application.dto.MemberDto;
import com.jinshuo.mall.service.user.application.qry.MemberQry;
import com.jinshuo.mall.service.user.application.qry.UserAccountQry;
import com.jinshuo.mall.service.user.service.command.UserAccountCmdService;
import com.jinshuo.mall.service.user.service.query.MemberQueryService;
import com.jinshuo.mall.service.user.service.query.UserAccountQueryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;

/**
 * 用户api
* @Description:
* @Author:         mgh
* @CreateDate:     2019/8/29 11:59
* @UpdateUser:     mgh
* @UpdateDate:     2019/8/29 11:59
* @UpdateRemark:
* @Version:        1.0
*/
@RestController
@Validated
@RequestMapping("/v1/manager/user")
@Api(description = "用户查询")
public class UserRestApi {

    @Autowired
    private UserAccountQueryService userAccountQueryService;

    @Autowired
    private UserAccountCmdService userAccountCmdService;

    @Autowired
    private MemberQueryService memberQueryService;


    /**
     * 查询会员列表
     * @param query
     * @return
     */
    @PostMapping("/query")
    @ApiOperation("查询会员列表")
    public WrapperResponse create(@RequestBody MemberQry query) throws UnsupportedEncodingException {
        PageInfo<MemberDto> page= memberQueryService.queryManagerList(query);
        return WrapperResponse.success(page);
    }


    /**
     * 查询后台用户列表
     * @param query
     * @return
     */
    @PostMapping("/queryManagerUser")
    @ApiOperation("查询后台用户列表")
    public WrapperResponse queryManagerUser(@RequestBody UserAccountQry query){
        PageInfo<MemberDto> page= userAccountQueryService.queryManagerUser(query);
        return WrapperResponse.success(page);
    }


    /**
     * 新增管理后台用户
     * @param cmd
     * @return
     */
    @PostMapping("/saveManagerUser")
    @ApiOperation("新增管理后台用户")
    public WrapperResponse saveManagerUser(@RequestBody UserManagerAccountCmd cmd){
        userAccountCmdService.saveManagerUser(cmd);
        return WrapperResponse.success();
    }

    /**
     * 查询管理后台用户信息
     * @param userAccountId 用户id
     * @return
     */
    @PostMapping("/queryManagerUserDetail")
    @ApiOperation("查询管理后台用户信息")
    public WrapperResponse queryManagerUserDetail(@RequestBody UserAccountId userAccountId) throws UnsupportedEncodingException {
        return WrapperResponse.success(userAccountQueryService.queryManagerUserDetail(userAccountId));
    }

    /**
     * 删除用户
     * @param userAccountId 用户id
     * @return
     */
    @PostMapping("/deleteManagerUser")
    @ApiOperation("删除管理后台用户")
    public WrapperResponse deleteManagerUser(@RequestBody UserAccountId userAccountId){
        userAccountCmdService.deleteManagerUser(userAccountId);
        return WrapperResponse.success();
    }


    @PostMapping("/modifyLoginPassword")
    @ApiOperation("修改登录密码")
    public WrapperResponse modifyLoginPassword(@RequestBody LoginPasswordCmd cmd) throws Exception {
        userAccountCmdService.updatePassword(cmd);
        return WrapperResponse.success();
    }
}
