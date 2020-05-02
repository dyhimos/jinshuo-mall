package com.jinshuo.mall.domain.user.model.member;

import com.jinshuo.core.model.IdentifiedEntity;
import com.jinshuo.core.utils.IdCardUtil;
import com.jinshuo.mall.domain.user.model.scoreAccount.MemberScoreAccount;
import com.jinshuo.mall.domain.user.model.scoreRecord.MemberScoreRecord;
import com.jinshuo.mall.domain.user.model.userAccount.UserAccountId;
import com.jinshuo.mall.domain.user.model.userAccountPlatform.SexEnums;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author dongyh
 * @Classname Member
 * @Description 会员信息
 * @Date 2019/6/16 :01
 * @Created by dongyh
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Member extends IdentifiedEntity {
    /**
     * 会员信息ID
     */
    private MemberId memberId;

    /**
     * 账户ID
     */
    private UserAccountId userAccountId;

    /**
     * 客户号
     */
    private Long memNo;

    /**
     * 店铺id
     */
    private Long shopId;

    /**
     * openid
     */
    private String openid;

    /**
     * 客户昵称
     */
    private String nickname;

    /**
     * 客户性别
     */
    private SexEnums sex;

    /**
     * 头像
     */
    private String avatar;

    /**
     * 年龄
     */
    private Integer age;

    /**
     * 出生年月日
     */
    private String birthday;

    /**
     * 会员等级
     */
    private Integer level;

    /**
     * 关注时间
     */
    private Date subscribeTime;

    /**
     * 会员类型:1、粉丝；2、会员；3、普通会员；4、分销员
     */
    private Integer type;

    /**
     * 会员来源：1、微信；2、小程序；3、app；4、PC；5、手工录入
     */
    private Integer sourceCanal;

    /**
     * 用户所在国家
     */
    private String country;

    /**
     * 用户所在省份
     */
    private String province;

    /**
     * 用户所在城市
     */
    private String city;

    /**
     * 是否分销会员 （0:团长  1：麦客 2：普通）
     */
    private Integer isDis;

    /**
     * 是否粉丝（1：是 0 :不是）
     */
    private Integer isFans;

    /**
     * 上级用户
     */
    private Long pid;

    /**
     * 成为麦客的时间
     */
    private Date pidTime;

    /**
     * 成为团长的时间
     */
    private Date commanderTime;


    /**
     * 消费总额
     */
    private BigDecimal consumeAmount;

    /**
     * 消费订单数
     */
    private Integer consumeOrder;

    /**
     * 最近消费时间
     */
    private Date consumeTime;

    /**
     * 父节点路径
     */
    private String path;

    /**
     * 父节点
     */
    private Member parentMember;

    /**
     * 会员账户
     */
    private MemberScoreAccount memberScoreAccount;

    /**
     * 消费记录
     */
    public MemberScoreRecord memberScoreRecord;

    /**
     * 身份证号
     */
    private String idCard;

    /**
     * 真实姓名
     */
    private String surname;
    /**
     * 手机号
     */
    private String phone;

    @ApiModelProperty(value = "支付宝账号")
    private String payNo;

    @ApiModelProperty(value = "微信号")
    private String wxNo;

    /**
     * 余额支付密码
     */
    private String payPassword;

    /**
     * 消费之后积分和积分记录
     */
    public static Member costAccount(Long userId, String orderNo, BigDecimal costAmount, Date consumeTime) {
        //Member member = SpringUtil.getBean(MemberRepo.class).queryByUserId(userId);
        /*this.consumeAmount = this.consumeAmount.add(costAmount);
        member.setConsumeTime(consumeTime);
        member.setConsumeOrder(member.getConsumeOrder() + 1);

        int costScore = costAmount.setScale(0, BigDecimal.ROUND_DOWN).intValue();
        //积分账户
        MemberScoreAccount memberScoreAccount = member.getMemberScoreAccount();
        memberScoreAccount.setTotalScore(memberScoreAccount.getTotalScore() + costScore);
        memberScoreAccount.setUseableScore(memberScoreAccount.getUseableScore() + costScore);

        //积分记录
        MemberScoreRecord memberScoreRecord = MemberScoreRecord.builder()
                .memberId(member.getMemberId())
                .score(costScore)
                .type(ScoreTypeEnums.TYPE_ADD)
                .expendScosourceType(1)
                .memberScoreRecordId(SpringUtil.getBean(ScoreRecordRepo.class).nextId())
                .sourceMemo("新增积分的订单号为：" + orderNo)
                .build();

        member.setMemberScoreAccount(memberScoreAccount);
        member.setMemberScoreRecord(memberScoreRecord);*/
        return null;
    }

    //完善客户信息
    public Member comMember(String surname, String idCard, String nickname, String avatar, String payNo, String wxNo) {
        this.surname = surname;
        this.nickname = nickname;
        this.avatar = avatar;
        this.payNo = payNo;
        this.wxNo = wxNo;
        if (null != idCard) {
            this.birthday = IdCardUtil.getBirthday(idCard);
            this.sex = SexEnums.getEnumByCode(IdCardUtil.getSex(idCard));
            this.age = IdCardUtil.getAge(idCard);
            this.idCard = idCard;
        }
        return this;
    }

    public void init() {
        super.preInsert();
    }
}
