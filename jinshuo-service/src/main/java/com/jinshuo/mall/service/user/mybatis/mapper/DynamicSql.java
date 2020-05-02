package com.jinshuo.mall.service.user.mybatis.mapper;

import com.jinshuo.mall.domain.user.model.member.Member;
import com.jinshuo.mall.domain.user.model.notice.Notice;
import com.jinshuo.mall.domain.user.model.role.Role;
import com.jinshuo.mall.domain.user.model.scoreRecord.MemberScoreRecord;
import com.jinshuo.mall.domain.user.model.userAccount.UserAccount;
import com.jinshuo.mall.domain.user.model.userAccountPlatform.UserAccountPlatform;
import com.jinshuo.mall.service.user.application.qry.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.jdbc.SQL;

/**
 * @author dongyh
 * @Title: Test
 * @ProjectName ym-center
 * @Description: TODO
 * @date 2019/7/17 15:06
 */
@Slf4j
public class DynamicSql {
    public String createUserAccountSql(UserAccount userAccount) {
        log.info("进入创建账户信息sql");
        return new SQL()
                .INSERT_INTO("user_account")
                .INTO_COLUMNS("id", "nickname", "username", "password", "type", "phone", "email", "invite_code", "shop_id", "create_ip_at", "last_login_ip_at", "login_count", "user_status")
                .INTO_VALUES("#{userAccountId.id}", "#{nickname}", "#{username}"
                        , "#{password}", "#{type}", "#{phone}", "#{email}", "#{inviteCode}", "#{shopId}"
                        , "#{createIpAt}", "#{lastLoginIpAt}", "#{loginCount}", "#{userStatus.code}"
                )
                .toString();
    }


    /**
     * 保存微信信息sql
     *
     * @param userAccountPlatform
     * @return
     */
    public String createUserAccountPlatformSql(UserAccountPlatform userAccountPlatform) {
        log.info("进入创建微信信息sql");
        return new SQL()
                .INSERT_INTO("user_account_platform")
                .INTO_COLUMNS("id", "user_id", "type", "nickname", "openid", "unionid", "avatar", "sex", "shop_id"
                        , "create_date", "update_date", "remarks", "status", "version"
                )
                .INTO_VALUES("#{userAccountPlatformId.id}", "#{userAccountId.id}"
                        , "#{type}", "#{nickname}", "#{openid}", "#{unionid}", "#{avatar}", "#{sex.code}", "#{shopId}"
                        , "#{createDate}", "#{updateDate}", "#{remarks}", "#{status.code}", "#{version}"
                )
                .toString();
    }


    /**
     * 更新微信信息sql
     *
     * @param userAccountPlatform
     * @return
     */
    public String updateUserAccountPlatformSql(UserAccountPlatform userAccountPlatform) {
        return new SQL() {{
            UPDATE("user_account_platform");
            //昵称
            if (StringUtils.isNotBlank(userAccountPlatform.getNickname())) {
                SET("nickname = #{nickname}");
            }
            //头像
            if (StringUtils.isNotBlank(userAccountPlatform.getAvatar())) {
                SET("avatar = #{avatar}");
            }
            WHERE("id = #{userAccountPlatformId.id}");
        }}.toString();
    }


    /**
     * 保存会员信息
     *
     * @param member
     * @return
     */
    public String createMemberSql(Member member) {
        log.info("保存会员信息sql");
        return new SQL()
                .INSERT_INTO("member")
                .INTO_COLUMNS("id",
                        "user_id",
                        "phone",
                        "openid",
                        "shop_id",
                        "mem_no",
                        "type",
                        "source_canal",
                        "nickname",
                        "sex",
                        "avatar",
                        "age",
                        "birthday",
                        "level",
                        "country",
                        "province",
                        "city",
                        "is_dis",
                        "is_fans",
                        "pid",
                        "pid_time",
                        "subscribe_time",
                        "path",
                        "consume_amount",
                        "consume_order",
                        "consume_time",
                        "create_date",
                        "update_date",
                        "remarks",
                        "status",
                        "version"
                )
                .INTO_VALUES("#{memberId.id}",
                        "#{userAccountId.id}",
                        "#{phone}",
                        "#{openid}",
                        "#{shopId}",
                        "#{memNo}",
                        "#{type}",
                        "#{sourceCanal}",
                        "#{nickname}",
                        "#{sex.code}",
                        "#{avatar}",
                        "#{age}",
                        "#{birthday}",
                        "#{level}",
                        "#{country}",
                        "#{province}",
                        "#{city}",
                        "#{isDis}",
                        "#{isFans}",
                        "#{pid}",
                        "#{pidTime}",
                        "#{subscribeTime}",
                        "#{path}",
                        "#{consumeAmount}",
                        "#{consumeOrder}",
                        "#{consumeTime}",
                        "#{createDate}",
                        "#{updateDate}",
                        "#{remarks}",
                        "#{status.code}",
                        "#{version}"
                )
                .toString();
    }


    /**
     * 更新会员信息
     *
     * @param member
     * @return
     */
    public String updateMemberInfoSql(Member member) {
        return new SQL() {{
            UPDATE("member");
            //昵称
            if (StringUtils.isNotBlank(member.getNickname())) {
                SET("nick_name = #{nickname}");
            }
            //头像
            if (StringUtils.isNotBlank(member.getAvatar())) {
                SET("avatar = #{avatar}");
            }
            //头像
            if (member.getIsFans() != null) {
                SET("is_fans = #{isFans}");
            }
            WHERE("id = #{memberId.id}");
        }}.toString();
    }

    /**
     * 更新会员信息
     *
     * @param member
     * @return
     */
    public String updateMemberSql(Member member) {
        return new SQL() {{
            UPDATE("member");
            //父节点id
            if (null != member.getPid()) {
                SET("pid = #{pid}");
            }
            //分销会员
            if (null != member.getIsDis()) {
                SET("is_dis = #{isDis}");
            }
            //消费金额
            if (null != member.getConsumeAmount()) {
                SET("consume_amount = #{consumeAmount}");
            }
            //订单数
            if (null != member.getConsumeOrder()) {
                SET("consume_order = #{consumeOrder}");
            }
            //成为麦客时间
            if (null != member.getPidTime()) {
                SET("pid_time = #{pidTime}");
            }
            //成为团长时间
            if (null != member.getCommanderTime()) {
                SET("commander_time = #{commanderTime}");
            }
            //最近消费时间
            if (null != member.getConsumeTime()) {
                SET("consume_time = #{consumeTime}");
            }
            WHERE("id =" + member.getMemberId().getId());
        }}.toString();
    }

    public String queryMemberListSql(MemberQry query) {
        return new SQL() {{
            SELECT("m.*");
            FROM("member m");
            LEFT_OUTER_JOIN("user_account u on m.user_id = u.id");
            WHERE("1=1");
            WHERE("u.type =1");
            ORDER_BY("m.create_date desc");
        }}.toString();
    }


    /**
     * 查询会员列表
     *
     * @param query
     * @return
     */
    /*public String queryMemberListSql(MemberQry query) {
        return new SQL() {{
            SELECT("*");
            FROM("member");
            WHERE("1=1");
            //
            if (StringUtils.isNotBlank(query.getPath())) {
                WHERE("path like '" + query.getPath() + ",%'");
            }
            if (StringUtils.isNotBlank(query.getName())) {
                WHERE("name like '%" + query.getName() + "%'");
            }
            if (StringUtils.isNotBlank(query.getParentName())) {
                WHERE("pid  in (select user_id from member where name like '%" + query.getParentName() + "%')");
            }
            if (query.getShopId() != null) {
                WHERE("shop_id = #{shopId}");
            }
            if (query.getIsDis() != null) {
                WHERE("is_dis = #{isDis}");
            }
            //粉丝
            if (query.getIsFans() != null) {
                WHERE("is_fans = #{isFans}");
            }
            if (query.getConsumeAmount() != null) {
                WHERE("consume_amount > #{consumeAmount}");
            }
            ORDER_BY("subscribe_time desc,create_date desc");
        }}.toString();
    }*/


    /**
     * 查询我的下级列表
     *
     * @param qry
     * @return
     */
    public String queryMySubordinateInfo(DisSalemanQry qry) {
        return new SQL() {{
            SELECT("*");
            FROM("member ");
            WHERE("pid=" + qry.getUserId());
            // 空：全部客户  1我的客户  2我的团队
            if (null == qry.getQryType()) {

            } else if (2 == qry.getQryType()) {
                WHERE("is_dis=1");
            } else if (1 == qry.getQryType()) {
                WHERE("is_dis=2");
            }
            ORDER_BY(" create_date DESC ");
        }}.toString();
    }


    /**
     * 查询积分记录
     *
     * @param memberScoreRecord
     * @return
     */
    public String queryScoreRecordSql(MemberScoreRecord memberScoreRecord) {
        return new SQL() {{
            SELECT("*");
            FROM("member_score_record ");
            if (memberScoreRecord.getMemberId() != null) {
                WHERE("mem_id=#{memberId.id}");
            }
        }}.toString();
    }


    /**
     * 完善用户信息
     *
     * @param member
     * @return
     */
    public String comInfo(Member member) {
        return new SQL() {{
            UPDATE("member");
            if (null != member.getSurname()) {
                SET("surname = #{surname}");
            }
            if (null != member.getAvatar()) {
                SET("avatar = #{avatar}");
            }
            if (null != member.getNickname()) {
                SET("nickname = #{nickname}");
            }
            if (null != member.getAge()) {
                SET("age = #{age}");
            }
            if (null != member.getBirthday()) {
                SET("birthday = #{birthday}");
            }
            if (null != member.getIdCard()) {
                SET("id_card = #{idCard}");
            }
            if (null != member.getSex()) {
                SET("sex = " + member.getSex().getCode());
            }
            if (null != member.getPayNo()) {
                SET("pay_no = #{payNo}");
            }
            if (null != member.getWxNo()) {
                SET("wx_no =  #{wxNo}");
            }
            WHERE("id =" + member.getMemberId().getId());
        }}.toString();
    }


    /**
     * 查询角色
     *
     * @param role
     * @return
     */
    public String queryRole(Role role) {
        return new SQL() {
            {
                SELECT("*");
                FROM("role ");
                WHERE("status = 1");
                if (role.getShopId() != null) {
                    WHERE("shop_id=#{shopId.id}");
                }
                if (StringUtils.isNotBlank(role.getCode())) {
                    WHERE("code=#{code}");
                }
                if (StringUtils.isNotBlank(role.getName())) {
                    WHERE("code=#{name}");
                }
            }
        }.toString();
    }


    /**
     * 根据供应商id查询订单信息
     *
     * @param qry
     * @return
     */
    public String countLogin(ManagerCountQry qry) {
        return new SQL() {{
            SELECT(" * "
            );
            FROM("login_report");
            WHERE("shop_id=" + qry.getShopId());
            if (2 == qry.getQryType()) {
                WHERE(" TO_DAYS( NOW( ) ) - TO_DAYS( create_date ) = 1 ");
            } else if (3 == qry.getQryType()) {
                WHERE(" DATE_SUB(CURDATE(), INTERVAL 7 DAY) <= date(create_date) ");
            } else if (4 == qry.getQryType()) {
                WHERE(" create_date  between #{startTime} AND #{endTime}");
            } else {
                WHERE(" to_days( create_date ) = to_days(now()) ");
            }
        }}.toString();
    }


    /**
     * 查询角色
     *
     * @param notice
     * @return
     */
    public String queryMyNotice(Notice notice) {
        return new SQL() {
            {
                SELECT(" * ");
                FROM(" user_notice ");
                WHERE(" status = 1");
                WHERE(" notice_status = 0");
                /*WHERE(" start_time < now()");
                WHERE(" end_time > now()");*/
                if (null != notice.getIsShow() && 0 == notice.getIsShow()) {
                    WHERE("is_show = " + notice.getIsShow());
                }
                ORDER_BY(" sort DESC ");
            }
        }.toString();
    }


    /**
     * 统计某个动作访问量
     *
     * @param qry
     * @return
     */
    public String countStatisticsList(StatisticsQry qry) {
        return new SQL() {
            {
                SELECT(" * ");
                FROM(" user_statistics ");
                WHERE(" status = 1");

                ORDER_BY(" create_date DESC ");
            }
        }.toString();
    }


    /**
     * 统计某个动作访问量
     *
     * @param qry
     * @return
     */
    public String countStatistics(VisitorCountQry qry) {
        return new SQL() {
            {
                SELECT(" * ");
                FROM(" user_statistics ");
                WHERE(" status = 1");
                if (null != qry.getStatisticsCode()) {
                    WHERE("statistics_code = '" + qry.getStatisticsCode() + "'");
                }
                if (2 == qry.getQryType()) {
                    WHERE(" TO_DAYS( NOW( ) ) - TO_DAYS( create_date ) = 1 ");
                } else if (3 == qry.getQryType()) {
                    WHERE(" DATE_SUB(CURDATE(), INTERVAL 7 DAY) <= date(create_date) ");
                } else if (4 == qry.getQryType()) {
                    WHERE(" create_date  between #{startTime} AND #{endTime}");
                } else {
                    WHERE(" to_days( create_date ) = to_days(now()) ");
                }
                ORDER_BY(" create_date DESC ");
            }
        }.toString();
    }


    /**
     * 统计某个动作访问量
     *
     * @param qry
     * @return
     */
    public String countMember(VisitorCountQry qry) {
        return new SQL() {
            {
                SELECT(" * ");
                FROM(" user_visitor_log ");
                WHERE(" status = 1 ");
                if (2 == qry.getQryType()) {
                    WHERE(" TO_DAYS( NOW( ) ) - TO_DAYS( visit_last_time ) = 1 ");
                } else if (3 == qry.getQryType()) {
                    WHERE(" DATE_SUB(CURDATE(), INTERVAL 7 DAY) <= date(visit_last_time) ");
                } else if (4 == qry.getQryType()) {
                    WHERE(" visit_last_time  between #{startTime} AND #{endTime}");
                } else {
                    WHERE(" to_days( visit_last_time ) = to_days(now()) ");
                }
                ORDER_BY(" create_date DESC ");
            }
        }.toString();
    }


}