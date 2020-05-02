package com.jinshuo.mall.service.user.mybatis.mapper;

import com.jinshuo.core.model.enums.Status;
import com.jinshuo.core.model.enums.handler.UniversalEnumHandler;
import com.jinshuo.mall.domain.user.model.member.Member;
import com.jinshuo.mall.domain.user.model.userAccountPlatform.SexEnums;
import com.jinshuo.mall.service.user.application.qry.DisSalemanQry;
import com.jinshuo.mall.service.user.application.qry.MemberQry;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @Classname MemberMapper
 * @Description TODO
 * @Date 2019/6/16 20:09
 * @Created by dongyh
 */
@Mapper
public interface MemberMapper {

    @InsertProvider(type = DynamicSql.class, method = "createMemberSql")
    void save(Member member);

    @UpdateProvider(type = DynamicSql.class, method = "updateMemberInfoSql")
    void update(Member member);


    @ResultMap(value = "memberResult")
    @Select("SELECT * FROM member WHERE id=#{id}")
    Member findById(Long id);

    @ResultMap(value = "memberResult")
    @Select({"<script>" +
            "select * member where id in"
            + "<foreach  collection = 'ids' item = 'id'  index ='index' open = '(' separator= ',' close = ')'>"
            + "	#{id} "
            + "</foreach>"
            + "</script>"})
    public List<Member> getByIds(List<Long> ids);


    @ResultMap(value = "memberResult")
    @SelectProvider(type = DynamicSql.class, method = "queryMemberListSql")
    List<Member> queryMemberList(MemberQry query);

    @Results(
            id = "memberResult",
            value = {
                    @Result(property = "memberId.id", column = "id"),
                    @Result(property = "userAccountId.id", column = "user_id"),
                    @Result(property = "openid", column = "openid"),
                    @Result(property = "idCard", column = "id_card"),
                    @Result(property = "surname", column = "surname"),
                    @Result(property = "phone", column = "phone"),
                    @Result(property = "shopId", column = "shop_id"),
                    @Result(property = "memNo", column = "mem_no"),
                    @Result(property = "nickname", column = "nickname"),
                    @Result(property = "sex", column = "sex", javaType = SexEnums.class, typeHandler = UniversalEnumHandler.class),
                    @Result(property = "avatar", column = "avatar"),
                    @Result(property = "age", column = "age"),
                    @Result(property = "birthday", column = "birthday"),
                    @Result(property = "level", column = "level"),
                    @Result(property = "country", column = "country"),
                    @Result(property = "province", column = "province"),
                    @Result(property = "city", column = "city"),
                    @Result(property = "isDis", column = "is_dis"),
                    @Result(property = "isFans", column = "is_fans"),
                    @Result(property = "pid", column = "pid"),
                    @Result(property = "pidTime", column = "pid_time"),
                    @Result(property = "subscribeTime", column = "subscribe_time"),
                    @Result(property = "commanderTime", column = "commander_time"),
                    @Result(property = "path", column = "path"),
                    @Result(property = "payNo", column = "pay_no"),
                    @Result(property = "wxNo", column = "wx_no"),
                    @Result(property = "payPassword", column = "pay_password"),
                    @Result(column = "pid", property = "parentMember", javaType = Member.class,
                            one = @One(select = "com.ym.wool.uc.infra.mybatis.mapper.MemberMapper.queryByUserId")),
                    @Result(column = "id", property = "memberScoreAccount", javaType = Member.class,
                            one = @One(select = "com.ym.wool.uc.infra.mybatis.mapper.ScoreAccountMapper.queryByMemId")),
                    @Result(property = "consumeAmount", column = "consume_amount"),
                    @Result(property = "consumeOrder", column = "consume_order"),
                    @Result(property = "consumeTime", column = "consume_time"),
                    @Result(property = "createDate", column = "create_date"),
                    @Result(property = "updateDate", column = "update_date"),
                    @Result(property = "remarks", column = "remarks"),
                    @Result(property = "version", column = "version"),
                    @Result(property = "status", column = "status", javaType = Status.class, typeHandler = UniversalEnumHandler.class)
            }
    )
    @Select("SELECT * FROM member  WHERE user_id=#{userId} LIMIT 1")
    Member queryByUserId(@Param("userId") Long userId);

    /**
     * 更新会员信息
     *
     * @param member
     */
    @UpdateProvider(type = DynamicSql.class, method = "updateMemberSql")
    void updateById(Member member);


    @ResultType(Integer.class)
    @Select("SELECT COUNT(1) FROM member WHERE  pid=#{userId} ")
    Integer getFromUser(@Param("userId") Long userId);


    /**
     * 查询下面麦客数量
     *
     * @param userId
     * @return
     */
    @ResultType(Integer.class)
    @Select("SELECT COUNT(1) FROM member WHERE  pid=#{userId} and is_dis = 1 ")
    Integer getFromUserAndDis(@Param("userId") Long userId);


    @ResultMap(value = "memberResult")
    @Select("SELECT * FROM member WHERE " +
            "  pid=#{userId} AND  is_dis = 2 ")
    List<Member> queryMyCustomer(@Param("userId") Long userId);


    /**
     * 查询我的直接会员数量
     *
     * @param userId
     * @return
     */
    @ResultMap(value = "memberResult")
    @Select("SELECT * FROM member WHERE " +
            "  pid=#{userId}")
    List<Member> queryAllMyCustomer(@Param("userId") Long userId);


    @ResultMap(value = "memberResult")
    @Select("SELECT * FROM member WHERE " +
            "  pid=#{userId} AND  is_dis = 1 ")
    List<Member> queryMySaleman(@Param("userId") Long userId);


    @ResultMap(value = "memberResult")
    @Select("SELECT * FROM member WHERE user_id = " +
            "  (SELECT pid FROM member WHERE user_id=#{userId})  AND (is_dis = 1 OR  is_dis = 0) ")
    Member queryMyUpperSaleman(@Param("userId") Long userId);

    @ResultMap(value = "memberResult")
    @SelectProvider(type = DynamicSql.class, method = "queryMySubordinateInfo")
    List<Member> queryMySubordinateInfo(DisSalemanQry query);


    /**
     * 更新为关注
     *
     * @param notSubscribeList
     * @param shopId
     */
    @Update({"<script>" +
            "update member set is_fans=1,update_date=now() where shop_id=#{shopId} and openid in"
            + "<foreach  collection = 'notSubscribeList' item = 'openid'  index ='index' open = '(' separator= ',' close = ')'>"
            + "	#{openid} "
            + "</foreach>"
            + "</script>"})
    void subscribeFans(@Param("notSubscribeList") List<String> notSubscribeList, @Param("shopId") Long shopId);


    /**
     * 更新为取消关注
     *
     * @param notSubscribeList
     * @param shopId
     */
    @Update({"<script>" +
            "update member set is_fans=0,update_date=now() where shop_id=#{shopId} and openid in"
            + "<foreach  collection = 'notSubscribeList' item = 'openid'  index ='index' open = '(' separator= ',' close = ')'>"
            + "	#{openid} "
            + "</foreach>"
            + "</script>"})
    void unSubscribeFans(@Param("notSubscribeList") List<String> notSubscribeList, @Param("shopId") Long shopId);

    @UpdateProvider(type = DynamicSql.class, method = "comInfo")
    int comInfo(Member member);


    @Update("UPDATE  member SET phone=#{phone} " +
            " WHERE id = #{memberId.id}")
    int updatePhone(Member Member);


    /**
     * 更新支付密码
     *
     * @param Member
     * @return
     */
    @Update("UPDATE  member SET pay_password=#{payPassword} " +
            " WHERE id = #{memberId.id}")
    int updatePayPassword(Member Member);

}
