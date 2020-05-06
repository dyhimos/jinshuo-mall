package com.jinshuo.mall.front.user.manager;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.jinshuo.core.response.WrapperResponse;
import com.jinshuo.mall.domain.user.model.member.Member;
import com.jinshuo.mall.service.user.application.dto.MemberDto;
import com.jinshuo.mall.service.user.application.qry.MemberQry;
import com.jinshuo.mall.service.user.mybatis.MemberRepo;
import com.jinshuo.mall.service.user.service.command.MemberCmdService;
import com.jinshuo.mall.service.user.service.query.MemberQueryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 会员api
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
@RequestMapping("/v1/manager/member")
@Api(description = "供应商查询")
public class MemberRestApi {

    @Autowired
    private MemberCmdService memberCmdService;

    @Autowired
    private MemberQueryService memberQueryService;

    @Autowired
    private MemberRepo memberRepo;


    @PostMapping("/test")
    public void test(){
       String receiveMsg = "{\"consumeAmount\":0.01,\"consumeTime\":1568258798026,\"eventVersion\":1,\"occurredOn\":1568258798026,\"orderNo\":\"DD1568258792020\",\"userId\":125554423664476160}";
        try{
            JSONObject json = JSON.parseObject(receiveMsg);
            Long userId = json.getLong("userId");
            if(userId!=null){
                String orderNo = json.getString("orderNo");
                BigDecimal consumeAmount = json.getBigDecimal("consumeAmount");
                Date consumeTime = json.getDate("consumeTime");
                Member  member = Member.costAccount(userId,orderNo,consumeAmount,consumeTime);

                //更新消费之后的积分信息，积分记录
                memberRepo.updateCost(member);

            }
        }catch(Exception e){
        }
    }


    /**
     * 查询订单列表
     * @param query
     * @return
     */
    @PostMapping("/query")
    @ApiOperation("查询会员列表")
    public WrapperResponse create(@RequestBody MemberQry query) throws UnsupportedEncodingException {
        PageInfo<MemberDto> page= memberQueryService.queryManagerList(query);
        return WrapperResponse.success(page);
    }
}
