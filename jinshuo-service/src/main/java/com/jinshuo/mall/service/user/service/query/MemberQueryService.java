package com.jinshuo.mall.service.user.service.query;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jinshuo.core.utils.MathUtil;
import com.jinshuo.core.utils.UserIdUtils;
import com.jinshuo.mall.domain.user.model.member.Member;
import com.jinshuo.mall.service.user.application.assermbler.MemberAssembler;
import com.jinshuo.mall.service.user.application.assermbler.UserMemberAssembler;
import com.jinshuo.mall.service.user.application.dto.MemberDto;
import com.jinshuo.mall.service.user.application.dto.MyUpgradeGapDto;
import com.jinshuo.mall.service.user.application.dto.UserMemberDto;
import com.jinshuo.mall.service.user.application.qry.DisSalemanQry;
import com.jinshuo.mall.service.user.application.qry.MemberQry;
import com.jinshuo.mall.service.user.mybatis.MemberRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by 19458 on 2019/8/13.
 */
@Slf4j
@Service
public class MemberQueryService {

    @Autowired
    private MemberRepo memberRepo;

  /*  @Autowired
    private FinanceServiceResponse financeServiceResponse;

    @Autowired
    private DistributionServiceResponse distributionServiceResponse;*/

    /**
     * 查询会员列表
     *
     * @param query
     * @return
     */
    public PageInfo<MemberDto> queryManagerList(MemberQry query) throws UnsupportedEncodingException {
        PageHelper.startPage(query.getPageNum(), query.getPageSize());
        List<Member> memberList = memberRepo.queryMemberList(query);
        List<MemberDto> memberDtos = new ArrayList<>();
        for (Member member : memberList) {
            memberDtos.add(MemberAssembler.assembleMemberDto(member));
        }
        PageInfo pageInfo = new PageInfo<>(memberList);
        pageInfo.setList(memberDtos);
        return pageInfo;
    }


    /**
     * 根据id获取所属分销员信息
     *
     * @param userId
     * @return
     * @DESC 查询上级
     */
    /*public SalemanDto getDisInfo(Long userId) {
        log.info(" -- 开始查询 " + userId + " 所属的分销员");
        SalemanDto dto = new SalemanDto();
        //查询下单人信息
        Member member1 = memberRepo.queryByUserId(userId);
        if (null == member1) {
            dto.setDisTag(false);
            return dto;
        }
        if (null != member1.getIsDis() && (1 == member1.getIsDis() || 0 == member1.getIsDis())) {
            //若自己是分销员则自己为自己的pid
            dto.setDisTag(true);
            dto.setPid(member1.getUserAccountId().getId());
        } else if (null != member1.getPid()) {
            //查询下单人的上级且必须是分销员
            Member member2 = memberRepo.queryByUserId(member1.getPid());
            if (null != member2 && null != member2.getIsDis() && (1 == member2.getIsDis() || 0 == member2.getIsDis())) {
                dto.setDisTag(true);
                dto.setPid(member2.getUserAccountId().getId());
            } else {
                dto.setDisTag(false);
                return dto;
            }
        } else {
            dto.setDisTag(false);
            return dto;
        }
        //查询第二级
        Member member3 = memberRepo.getMyUpperSaleman(dto.getPid());
        if (null != member3) {
            dto.setPid2(member3.getUserAccountId().getId());
        }
        //dto.setPids(getMyUpperMember("", userId));
        if (null != member1.getShopId()) {
            dto.setShopId(member1.getShopId());
        }
        log.info(" -- 查询" + userId + "所属的分销员结束，返回结果: " + JSONObject.toJSONString(dto));
        return dto;
    }

    *//**
     * 查询我的客户数量
     *
     * @param memId
     * @return
     *//*
    public SalemanCountDto getMyCustomer(Long memId) {
        //我的团队数量
        List<Member> smList = memberRepo.getMySaleman(memId);
        Integer smQuantity = smList.size();

        //我的客户数量
        List<Member> uqList = memberRepo.getMyCustomer(memId);
        Integer orderCount1 = 0;
        Integer orderCount2 = 0;
        try {
            if (null != smList && smList.size() > 0) {
                orderCount1 = smList.stream().mapToInt(Member::getConsumeOrder).sum();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            if (null != uqList && uqList.size() > 0) {
                orderCount2 = uqList.stream().mapToInt(Member::getConsumeOrder).sum();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        Integer userQuantity = uqList.size();
        SalemanCountDto dto = new SalemanCountDto(smQuantity, userQuantity, smQuantity + userQuantity, orderCount1 + orderCount2);
        log.info(" -- 查询我的客户数量,返回参数：" + JSONObject.toJSONString(dto));
        return dto;
    }*/

    /**
     * 查询我的上级id集合
     *
     * @param userId
     * @return
     */
    public String getMyUpperMember(String userIds, Long userId) {
        log.info(" -- 查询我的上级id集合,输入参数：" + userId);
        Member member = memberRepo.queryByUserId(userId);
        if (member != null && null != member.getPid()) {
            userIds += (member.getPid().toString() + ",");
            userIds = getMyUpperMember(userIds, member.getPid());
        }
        return userIds;
    }

    /**
     * 查询我的下级客户信息
     *
     * @param qry
     * @return
     */
    public PageInfo getMySubordinateInfo(DisSalemanQry qry) {
        log.info(" -- 查询我的下级客户信息,输入参数：" + qry);
        if (null == qry.getUserId()) {
            qry.setUserId(UserIdUtils.getUserId());
        }
        PageHelper.startPage(qry.getPageNum(), qry.getPageSize());
        List<Member> members = memberRepo.findMySubordinateInfo(qry);
        PageInfo pageInfo = new PageInfo(members);
        /*List<UserMemberDto> dtos = members.stream()
                .map(member -> UserMemberAssembler.assembleUserAccount(member))
                .map(userMemberDto -> {
                    CustomerScoreDto dto = financeServiceResponse.getMyCommByUserId(Long.parseLong(userMemberDto.getId()));
                    userMemberDto.changeDisAmount(dto.getConsumeOrder(), dto.getConsumeAmount(), dto.getDisAmount());
                    return userMemberDto;
                })
                .collect(Collectors.toList());
        pageInfo.setList(dtos);*/
        return pageInfo;
    }

    /**
     * 获取我的粉丝集合
     *
     * @return
     */
    public List<UserMemberDto> getMyFans() {
        DisSalemanQry qry = new DisSalemanQry();
        qry.setUserId(UserIdUtils.getUserId());
        qry.setPageSize(30);
        List<Member> members = memberRepo.findMySubordinateInfo(qry);
        List<UserMemberDto> dtos = members.stream().map(member -> UserMemberAssembler.assembleUserAccount(member)).collect(Collectors.toList());
        return dtos;
    }

    /**
     * 查询我与上级的差距
     *
     * @return
     */
    public MyUpgradeGapDto getGap() {
        log.info(" -- 查询我与上级的差距");
        MyUpgradeGapDto result = new MyUpgradeGapDto();
        result.setUpgradeNumberSwitch(1);
        result.setUpgradeSalesSwitch(1);
        result.setSubordinateSwitch(1);
        //DisSetDto dto = distributionServiceResponse.getDisRule();
        Member member = memberRepo.queryByUserId(UserIdUtils.getUserId());
        Integer upgradeCount = 0;
        BigDecimal upgradeAmount = new BigDecimal(0);
        Integer gapCount = 0;
        Integer subordinateCount = 0;
        Integer gapSubordinateCount = 0;
        BigDecimal gapAmount = new BigDecimal(0);
        BigDecimal temp = new BigDecimal(0);
        DisSalemanQry qry = new DisSalemanQry();
        qry.setUserId(UserIdUtils.getUserId());
        //查询名下所有
        List<Member> members = memberRepo.findMySubordinateInfo(qry);
        BigDecimal amount = new BigDecimal(0);
        if (null != members && members.size() > 0) {
            amount = members.stream().map(Member::getConsumeAmount).reduce(BigDecimal.ZERO, BigDecimal::add);
        }
        amount = MathUtil.add(amount, member.getConsumeAmount(), 2);
        /*if (2 == member.getIsDis()) {
            if (0 == dto.getUpgradeNumberSwitch1()) {
                upgradeCount = dto.getUpgradeNumber1();
                gapCount = upgradeCount - members.size();
                if (gapCount < 1) {
                    result.setIsComCount(0);
                }
                result.setUpgradeNumberSwitch(0);
            }
            if (0 == dto.getUpgradeSalesSwitch1()) {
                upgradeAmount = dto.getUpgradeSales1();
                gapAmount = MathUtil.subtract(upgradeAmount, amount, 2);
                if (-1 == gapAmount.compareTo(temp) || 0 == gapAmount.compareTo(temp)) {
                    result.setIsComAmount(0);
                }
                result.setUpgradeSalesSwitch(0);
            }
        } else if (1 == member.getIsDis()) {
            if (0 == dto.getUpgradeNumberSwitch2()) {
                upgradeCount = dto.getUpgradeNumber2();
                gapCount = upgradeCount - getFansByType(members, 2);
                if (gapCount < 1) {
                    result.setIsComCount(0);
                }
                result.setUpgradeNumberSwitch(0);
            }
            if (0 == dto.getSubordinateSwitch2()) {
                subordinateCount = dto.getSubordinateCount2();
                gapSubordinateCount = dto.getSubordinateCount2() - getFansByType(members, 1) - getFansByType(members, 0);
                if (gapSubordinateCount < 1) {
                    result.setIsComSubord(0);
                }
                result.setSubordinateSwitch(0);
            }
            if (0 == dto.getUpgradeSalesSwitch2()) {
                upgradeAmount = dto.getUpgradeSales2();
                gapAmount = MathUtil.subtract(upgradeAmount, amount, 2);
                if (-1 == gapAmount.compareTo(temp) || 0 == gapAmount.compareTo(temp)) {
                    result.setIsComAmount(0);
                }
                result.setUpgradeSalesSwitch(0);
            }
        } else {
            if (0 == dto.getUpgradeNumberSwitch1()) {
                upgradeCount = dto.getUpgradeNumber1();
                result.setUpgradeNumberSwitch(0);
            }
            if (0 == dto.getUpgradeSalesSwitch1()) {
                upgradeAmount = dto.getUpgradeSales1();
                result.setUpgradeSalesSwitch(0);
            }
        }*/
        result.setUpgradeNumber(upgradeCount);
        result.setUpgradeSales(upgradeAmount);
        result.changegapAmount(gapAmount);
        result.changeGapCount(gapCount);
        result.setSubordinateCount(subordinateCount);
        result.setGapSubordinateCount(gapSubordinateCount);
        return result;
    }

    public Integer getFansByType(List<Member> members, Integer type) {
        if (null == members || members.size() < 1) {
            return 0;
        }
        Map<Integer, List<Member>> map = members.stream().collect(Collectors.groupingBy(Member::getIsDis));
        if (map.containsKey(type)) {
            return map.get(type).size();
        }
        return 0;
    }

    public UserMemberDto findByUserId() {
       /* DisSalemanDto disSalemanDto = distributionServiceResponse.getDisByMemId(UserIdUtils.getUserId());
        UserMemberDto result = null;
        try {
            result = UserMemberAssembler.assembleUserAccount(memberRepo.queryByUserId(UserIdUtils.getUserId()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (null != result ) {
            if (null != disSalemanDto.getPayChannel()) {
                result.setPayChannel(disSalemanDto.getPayChannel());
            }
            if (StringUtils.isNotBlank(disSalemanDto.getPayNo())) {
                result.setPayNo(disSalemanDto.getPayNo());
            }
            if (StringUtils.isNotBlank(disSalemanDto.getWxNo())) {
                result.setWxNo(disSalemanDto.getWxNo());
            }
            if (StringUtils.isNotBlank(disSalemanDto.getSurname())) {
                result.setSurname(disSalemanDto.getSurname());
            }
        }
        return result;*/

        Member member = memberRepo.queryByUserId(UserIdUtils.getUserId());
        UserMemberDto dto = UserMemberAssembler.assembleUserAccount(member);
       return dto;
    }

}
