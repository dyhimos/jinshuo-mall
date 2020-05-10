package com.jinshuo.mall.service.social.mybatis.mapper;

import com.jinshuo.mall.service.social.application.qry.SocialTopicQry;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.jdbc.SQL;

/**
 * Created by 19458 on 2019/12/26.
 */
public class SocialDynamicSql {

    /**
     * 查询帖子
     *
     * @param qry
     * @return
     */
    public String queryTopicByExmple(SocialTopicQry qry) {
        return new SQL() {
            {
                SELECT(" * ");
                FROM("social_topic ");
                WHERE(" status = 1");
                if (null != qry.getShopId()) {
                    WHERE(" shop_id = " + qry.getShopId());
                }
                if (StringUtils.isNotBlank(qry.getTitle())) {
                    WHERE(" title like '%" + qry.getTitle() + "%' ");
                }
                if (StringUtils.isNotBlank(qry.getContents())) {
                    WHERE(" contents like '%" + qry.getContents() + "%' ");
                }
                if (null != qry.getIsTop()) {
                    WHERE(" is_top = " + qry.getIsTop());
                }
                if (null != qry.getUserId()) {
                    WHERE(" user_id = " + qry.getUserId());
                }
                if (null != qry.getCircleId()) {
                    WHERE(" circle_id = " + qry.getCircleId());
                }
                if (null != qry.getAuditStatus()) {
                    WHERE(" audit_status = " + qry.getAuditStatus());
                }
                if (null != qry.getShieldUserId()) {
                    WHERE(shieldModel(qry.getShieldUserId()));
                }
                WHERE(" status = 1");
                if (null != qry.getAuditUser()) {
                    WHERE(" audit_user = " + qry.getAuditUser());
                }
                if (null != qry.getAttr()) {
                    if (1 == qry.getAttr()) {
                        WHERE(" attr = " + qry.getAttr());
                        ORDER_BY(" weight DESC,update_date DESC");
                    } else if (2 == qry.getAttr()) {
                        ORDER_BY(" reply_count DESC");
                    } else {
                        ORDER_BY(" create_date DESC,weight DESC");
                    }
                } else {
                    ORDER_BY(" weight DESC,create_date DESC");
                }
            }
        }.toString();
    }


    /**
     * 查询我收藏的帖子
     *
     * @param qry
     * @return
     */
    public String queryMyCollecttopic(SocialTopicQry qry) {
        return new SQL() {
            {
                SELECT(" st.* ");
                FROM("social_topic st,social_topic_collect stc");
                WHERE(" st.status = 1 AND stc.status = 1 AND st.id = stc.topic_id");
                WHERE(" stc.user_id = " + qry.getUserId());
                if (StringUtils.isNotBlank(qry.getTitle())) {
                    WHERE(" st.title like '%" + qry.getTitle() + "%' ");
                }
                if (StringUtils.isNotBlank(qry.getContents())) {
                    WHERE(" st.contents like '%" + qry.getContents() + "%' ");
                }
                if (null != qry.getAttr()) {
                    WHERE(" st.attr = " + qry.getAttr());
                }
                if (null != qry.getIsTop()) {
                    WHERE(" st.is_top = " + qry.getIsTop());
                }
                if (null != qry.getCircleId()) {
                    WHERE(" st.circle_id = " + qry.getCircleId());
                }
                if (null != qry.getAuditStatus()) {
                    WHERE(" st.audit_status = " + qry.getAuditStatus());
                }
                if (null != qry.getAuditUser()) {
                    WHERE(" st.audit_user = " + qry.getAuditUser());
                }
                ORDER_BY("st.weight DESC,update_date DESC");
            }
        }.toString();
    }

    /**
     * 查询屏蔽模块
     *
     * @param userId
     * @return
     */
    public String shieldModel(Long userId) {
        String sql = "";
        if (null != userId) {
            sql = " id not in (select target_id from social_topic_shield where status = 1 and shield_type = 0 and user_id = " + userId + ") and " +
                    "user_id not in (select target_id from social_topic_shield where status = 1 and shield_type = 1 and user_id = " + userId + ") ";
        }
        return sql;
    }
}
