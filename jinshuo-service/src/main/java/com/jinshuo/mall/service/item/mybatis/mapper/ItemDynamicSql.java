package com.jinshuo.mall.service.item.mybatis.mapper;

import com.jinshuo.mall.domain.item.ad.Advertisement;
import com.jinshuo.mall.service.item.application.qry.*;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.jdbc.SQL;

import java.math.BigDecimal;

/**
 * Created by 19458 on 2019/7/24.
 */
public class ItemDynamicSql {


    /**
     * 前台查询产品（优化后）
     *
     * @param spuQry
     * @return
     */
    public String frontQuerySpuSql(SpuQry spuQry) {
        return new SQL() {
            {
                SELECT("gs.id," +
                        "gs.sort," +
                        "gs.name," +
                        "gs.is_hot," +
                        "gs.price," +
                        "gs.market_price," +
                        "gs.type_id," +
                        "gs.picture_url," +
                        "gs.poster," +
                        "gs.tag," +
                        "gs.is_dis," +
                        "gso.buy_end_date," +
                        "gs.area_names," +
                        "gso.activity_address");
                FROM("goods_spu gs");
                LEFT_OUTER_JOIN("goods_spu_other gso ON gs.id = gso.spu_id");
                WHERE("gs.status = 1");
                WHERE("now() > gso.up_time");
                WHERE("now() < gso.down_time");
                WHERE("gs.goods_status in(0)");
                if (StringUtils.isNotBlank(spuQry.getValueIds())) {
                    WHERE("gs.id in" + spuQry.getValueIds());
                }
                if (null != spuQry.getShopId()) {
                    WHERE("gs.shop_id = " + spuQry.getShopId());
                }
                // 1：城市匹配
                if (StringUtils.isNotBlank(spuQry.getAreaName())) {
                    WHERE("gs.area_names like '%" + spuQry.getAreaName() + "%'");
                }
                if (null == spuQry.getCategoryId()) {
                    WHERE("gs.category_id!=6");
                }
                if (null != spuQry.getCategoryId()) {
                    WHERE("gs.category_id  like '%" + spuQry.getCategoryId() + "%'");
                }
                if (StringUtils.isNotBlank(spuQry.getName())) {
                    WHERE("gs.name LIKE '%" + spuQry.getName() + "%'");
                }
                // 1：热门
                if (null != spuQry.getIsHot()) {
                    WHERE("gs.is_hot = " + spuQry.getIsHot());
                }
                // 1：最新开抢
                if ("1".equals(spuQry.getQryType())) {
                    WHERE("gso.is_scare_buy = 0");
                    WHERE("gso.buy_start_date < now()  ");
                    WHERE("now() < gso.buy_end_date ");
                }
                // 1：开抢预告（即将开抢）
                if ("2".equals(spuQry.getQryType())) {
                    WHERE("gso.is_scare_buy = 0");
                    WHERE("gso.buy_start_date > NOW() ");
                }
                //根据产品参数查询
                if (null != spuQry.getParamValueIds() && spuQry.getParamValueIds().size() > 0) {
                    WHERE("gs.id in ( " + querySpuWithParam(spuQry) + " ) ");
                }
                if (spuQry.getTags() != null && spuQry.getTags().size() > 0) {
                    String tags = "(";
                    for (Long tag : spuQry.getTags()) {
                        tags += tag + ",";
                    }
                    tags = tags.substring(0, tags.length() - 1);
                    tags = tags + ")";
                    WHERE(" gs.id IN (SELECT spu_id FROM goods_spu_tag WHERE status=1 AND tag_id in " + tags + ")");
                }
                ORDER_BY("gs.sort DESC,gs.update_date DESC");
            }
        }.toString();
    }


    /**
     * 前台查询优品
     *
     * @param spuQry
     * @return
     */
    public String frontQueryExcellentSpuSql(SpuQry spuQry) {
        return new SQL() {
            {
                SELECT("gs.id," +
                        "gs.sort," +
                        "gs.name," +
                        "gs.is_hot," +
                        "gs.price," +
                        "gs.market_price," +
                        "gs.type_id," +
                        "gs.picture_url," +
                        "gs.poster," +
                        "gs.tag," +
                        "gs.is_dis," +
                        "gso.buy_end_date," +
                        "gso.activity_address");
                FROM("goods_spu gs");
                LEFT_OUTER_JOIN("goods_spu_other gso ON gs.id = gso.spu_id");
                WHERE("gs.status = 1");
                WHERE("now() > gso.up_time");
                WHERE("now() < gso.down_time");
                WHERE("gs.goods_status in(0)");
                if (null == spuQry.getFeatureId()) {
                    WHERE("gs.category_id like '%130264886289629006%' ");
                } else {
                    WHERE("gs.feature_id=" + spuQry.getFeatureId());
                }
                if (null != spuQry.getShopId()) {
                    WHERE("gs.shop_id = " + spuQry.getShopId());
                }
                if (null != spuQry.getCount()) {
                    ORDER_BY("gs.sort DESC,gs.update_date DESC LIMIT " + spuQry.getCount());
                } else {
                    ORDER_BY("gs.sort DESC,gs.update_date DESC");
                }
            }
        }.toString();
    }

    /**
     * 前台查询分销产品
     *
     * @param spuQry
     * @return
     */
    public String queryDis(SpuQry spuQry) {
        return new SQL() {
            {
                SELECT("gs.id," +
                        "gs.sort," +
                        "gs.name," +
                        "gs.is_hot," +
                        "gs.price," +
                        "gs.market_price," +
                        "gs.type_id," +
                        "gs.picture_url," +
                        "gs.poster," +
                        "gs.tag," +
                        "gs.is_dis," +
                        "gso.buy_end_date," +
                        "gso.activity_address");
                FROM("goods_spu gs");
                LEFT_OUTER_JOIN("goods_spu_other gso ON gs.id = gso.spu_id");
                WHERE("gs.status = 1");
                WHERE("now() > gso.up_time");
                WHERE("now() < gso.down_time");
                WHERE("gs.goods_status in(0)");
                WHERE("gs.is_dis=0");
                if (null != spuQry.getShopId()) {
                    WHERE("gs.shop_id = " + spuQry.getShopId());
                }
                ORDER_BY("gs.sort DESC,gs.update_date DESC");
            }
        }.toString();
    }

    /**
     * 后台查询产品
     *
     * @param spuQry
     * @return
     */
    public String querySpuSql(SpuQry spuQry) {
        return new SQL() {
            {
                SELECT("gs.id," +
                        "gs.name," +
                        "gs.sketch," +
                        "gs.category_id," +
                        "gs.shop_id," +
                        "gs.type_id," +
                        "gs.supplier_id," +
                        "gs.brand_id," +
                        "gs.groud_id," +
                        "gs.tag," +
                        "gs.picture_url," +
                        "gs.spu_code," +
                        "gs.unit," +
                        "gs.price," +
                        "gs.market_price," +
                        "gs.cost_price," +
                        "gs.stock," +
                        "gs.warning_stock," +
                        "gs.virtual_sales," +
                        "gs.is_integral," +
                        "gs.integral," +
                        "gs.sort," +
                        "gs.goods_status," +
                        "gs.status," +
                        "gs.version," +
                        "gs.remarks," +
                        "gs.create_date," +
                        "gs.is_hot," +
                        "gs.is_dis," +
                        "gs.is_sendcode," +
                        "gs.reserve_address," +
                        "gs.poster," +
                        "gs.services," +
                        "gs.real_sales," +
                        "gs.update_date");
                FROM("goods_spu gs");
                LEFT_OUTER_JOIN("goods_spu_other gso ON gs.id = gso.spu_id");
                WHERE("gs.status = 1");
                // 3:已结束抢购
                if (StringUtils.isNotBlank(spuQry.getQryType()) && "3".equals(spuQry.getQryType())) {
                    WHERE(" gso.buy_end_date < now()");
                    //最先结束的排后面
                    if (null == spuQry.getSortType()) {
                        spuQry.setSortType(8);
                    }
                }
                //4:即将结束抢购
                if (StringUtils.isNotBlank(spuQry.getQryType()) && "4".equals(spuQry.getQryType())) {
                    WHERE(" gso.buy_end_date > now()");
                    //最先结束的排前面
                    if (null == spuQry.getSortType()) {
                        spuQry.setSortType(9);
                    }
                }
                if (null != spuQry.getIsDis()) {
                    WHERE("gs.is_dis = " + spuQry.getIsDis());
                }
                if (null != spuQry.getShopId()) {
                    WHERE("gs.shop_id = " + spuQry.getShopId());
                }
                if (null != spuQry.getSupplierId()) {
                    WHERE("gs.supplier_id = " + spuQry.getSupplierId());
                }
                if (null != spuQry.getTag()) {
                    WHERE(" gs.id IN (SELECT spu_id FROM goods_spu_tag WHERE status=1 AND tag_id =  " + spuQry.getTag() + ")");
                }
                if (null != spuQry.getGoodsStatus()) {
                    WHERE("gs.goods_status  =" + spuQry.getGoodsStatus());
                }
                if (StringUtils.isNotBlank(spuQry.getSpuName())) {
                    WHERE("gs.name LIKE '%" + spuQry.getSpuName() + "%'");
                }
                if (StringUtils.isNotBlank(spuQry.getName())) {
                    WHERE("gs.name LIKE '%" + spuQry.getName() + "%'");
                }
                if (null != spuQry.getBrandId()) {
                    WHERE("gs.brand_id = " + spuQry.getBrandId());
                }
                if (null != spuQry.getCategoryId()) {
                    WHERE("gs.category_id  like '%" + spuQry.getCategoryId() + "%'");
                }
                // 1：热门
                if (null != spuQry.getIsHot()) {
                    WHERE("gs.is_hot = " + spuQry.getIsHot());
                }
                // 1：最新开抢
                if ("1".equals(spuQry.getQryType())) {
                    WHERE("gso.is_scare_buy = 0");
                    WHERE("gso.buy_start_date < now()  ");
                    WHERE("now() < gso.buy_end_date ");
                }
                // 1：开抢预告
                if ("2".equals(spuQry.getQryType())) {
                    WHERE("gso.is_scare_buy = 0");
                    WHERE("gso.buy_start_date > NOW() ");
                }

                if (spuQry.getTags() != null && spuQry.getTags().size() > 1) {
                    String tags = "(";
                    for (Long tag : spuQry.getTags()) {
                        tags += tag + ",";
                    }
                    tags = tags.substring(0, tags.length() - 1);
                    tags = tags + ")";
                    WHERE(" gs.id IN (SELECT spu_id FROM goods_spu_tag WHERE status=1 AND tag_id in " + tags + ")");
                }
                // 1：销售价 2：库存 3：销量 4：权重
                if (null != spuQry.getSortType()) {
                    if (1 == spuQry.getSortType()) {
                        if (null != spuQry.getIsAscending() && 0 == spuQry.getIsAscending()) {
                            ORDER_BY("gs.price ");
                        } else {
                            ORDER_BY("gs.price DESC");
                        }
                    }
                    if (2 == spuQry.getSortType()) {
                        if (null != spuQry.getIsAscending() && 0 == spuQry.getIsAscending()) {
                            ORDER_BY("gs.stock ");
                        } else {
                            ORDER_BY("gs.stock DESC");
                        }
                    }
                    if (3 == spuQry.getSortType()) {
                        if (null != spuQry.getIsAscending() && 0 == spuQry.getIsAscending()) {
                            ORDER_BY("gs.real_sales ");
                        } else {
                            ORDER_BY("gs.real_sales DESC");
                        }
                    }
                    if (4 == spuQry.getSortType()) {
                        if (null != spuQry.getIsAscending() && 0 == spuQry.getIsAscending()) {
                            ORDER_BY("gs.sort ");
                        } else {
                            ORDER_BY("gs.sort DESC");
                        }
                    }
                    if (5 == spuQry.getSortType()) {
                        if (null != spuQry.getIsAscending() && 0 == spuQry.getIsAscending()) {
                            ORDER_BY("gs.create_date ");
                        } else {
                            ORDER_BY("gs.create_date DESC ");
                        }
                    }
                    if (8 == spuQry.getSortType()) {
                        ORDER_BY(" gso.buy_end_date DESC");
                    }
                    if (9 == spuQry.getSortType()) {
                        ORDER_BY(" gso.buy_end_date ");
                    }
                } else {
                    ORDER_BY("gs.sort DESC,gs.update_date DESC");
                }
            }
        }.toString();
    }


    /**
     * 前端查询产品新(分销市场查询)
     *
     * @param spuQry
     * @return
     */
    public String querySpuSqlNew(SpuQry spuQry) {
        return new SQL() {
            {
                SELECT("gs.id," +
                        "gs.name," +
                        "gs.sketch," +
                        "gs.category_id," +
                        "gs.shop_id," +
                        "gs.type_id," +
                        "gs.supplier_id," +
                        "gs.brand_id," +
                        "gs.groud_id," +
                        "gs.tag," +
                        "gs.picture_url," +
                        "gs.spu_code," +
                        "gs.unit," +
                        "gs.price," +
                        "gs.market_price," +
                        "gs.cost_price," +
                        "gs.stock," +
                        "gs.warning_stock," +
                        "gs.virtual_sales," +
                        "gs.is_integral," +
                        "gs.integral," +
                        "gs.sort," +
                        "gs.goods_status," +
                        "gs.status," +
                        "gs.version," +
                        "gs.remarks," +
                        "gs.create_date," +
                        "gs.is_hot," +
                        "gs.is_dis," +
                        "gs.is_sendcode," +
                        "gs.reserve_address," +
                        "gs.poster," +
                        "gs.real_sales," +
                        "gs.update_date");
                FROM("goods_spu_shop gss");
                LEFT_OUTER_JOIN("goods_spu gs ON gss.spu_id=gs.id");
                LEFT_OUTER_JOIN("goods_spu_other gso ON gs.id = gso.spu_id");
                WHERE("gss.status =1");
                WHERE("gss.shop_id =" + spuQry.getShopId());
                WHERE("gs.status = 1");
                if (null == spuQry.getCategoryId()) {
                    WHERE("gs.category_id!=6");
                }
                if (null == spuQry.getFlag()) {
                    //前台查询默认条件
                    WHERE("now() > gso.up_time");
                    WHERE("now() < gso.down_time");
                    WHERE("gs.goods_status in(0)");
                }
                if (null != spuQry.getGoodsStatus()) {
                    WHERE("gs.goods_status  =" + spuQry.getGoodsStatus());
                }
                if (StringUtils.isNotBlank(spuQry.getSpuName())) {
                    WHERE("gs.name LIKE '%" + spuQry.getSpuName() + "%'");
                }
                if (StringUtils.isNotBlank(spuQry.getName())) {
                    WHERE("gs.name LIKE '%" + spuQry.getName() + "%'");
                }
                if (null != spuQry.getBrandId()) {
                    WHERE("gs.brand_id = " + spuQry.getBrandId());
                }
                if (null != spuQry.getCategoryId()) {
                    WHERE("gs.category_id  like '%" + spuQry.getCategoryId() + "%'");
                }
                // 1：热门
                if (null != spuQry.getIsHot()) {
                    WHERE("gs.is_hot = " + spuQry.getIsHot());
                }
                // 1：最新开抢
                if ("1".equals(spuQry.getQryType())) {
                    WHERE("gso.is_scare_buy = 0");
                    WHERE("gso.buy_start_date < now()  ");
                    WHERE("now() < gso.buy_end_date ");
                }
                // 1：开抢预告
                if ("2".equals(spuQry.getQryType())) {
                    WHERE("gso.is_scare_buy = 0");
                    WHERE("gso.buy_start_date > NOW() ");
                }

                if (spuQry.getTags() != null && spuQry.getTags().size() > 1) {
                    String tags = "(";
                    for (Long tag : spuQry.getTags()) {
                        tags += tag + ",";
                    }
                    tags = tags.substring(0, tags.length() - 1);
                    tags = tags + ")";
                    WHERE(" gs.id IN (SELECT spu_id FROM goods_spu_tag WHERE status=1 AND tag_id in " + tags + ")");
                }
                // 1：销售价 2：库存 3：销量 4：权重
                if (null != spuQry.getSortType()) {
                    if (1 == spuQry.getSortType()) {
                        if (null != spuQry.getIsAscending() && 0 == spuQry.getIsAscending()) {
                            ORDER_BY("gs.price ");
                        } else {
                            ORDER_BY("gs.price DESC");
                        }
                    }
                    if (2 == spuQry.getSortType()) {
                        if (null != spuQry.getIsAscending() && 0 == spuQry.getIsAscending()) {
                            ORDER_BY("gs.stock ");
                        } else {
                            ORDER_BY("gs.stock DESC");
                        }
                    }
                    if (3 == spuQry.getSortType()) {
                        if (null != spuQry.getIsAscending() && 0 == spuQry.getIsAscending()) {
                            ORDER_BY("gs.real_sales ");
                        } else {
                            ORDER_BY("gs.real_sales DESC");
                        }
                    }
                    if (4 == spuQry.getSortType()) {
                        if (null != spuQry.getIsAscending() && 0 == spuQry.getIsAscending()) {
                            ORDER_BY("gs.sort ");
                        } else {
                            ORDER_BY("gs.sort DESC");
                        }
                    }
                } else {
                    ORDER_BY("gs.sort DESC,gs.update_date DESC");
                }
            }
        }.toString();
    }


    public String queryCouponSql(CouponQry couponQry) {
        return new SQL() {
            {
                SELECT("*");
                FROM("coupon");
                WHERE(
                        " status=1 "
                );
                WHERE("now() > receive_start_time");
                WHERE("now() < receive_end_time");
                WHERE("shop_id=" + couponQry.getShopId());
                WHERE("is_show = 0 ");
                if (null != couponQry.getTargetId()) {
                    if (null != couponQry.getCouponIds() && couponQry.getCouponIds().size() > 0) {
                        String ids = "(";
                        for (Long id : couponQry.getCouponIds()) {
                            ids += id + ",";
                        }
                        ids = ids.substring(0, ids.length() - 1);
                        ids = ids + ")";
                        WHERE("( scope_type=1 or (scope_type=2 and id in " + ids + " ) or (scope_type = 3 and id not in" + ids + "))");
                    }
                }
            }

        }.toString();
    }


    /**
     * 根据主题查询已选中的产品（后台）
     *
     * @param qry
     * @return
     */
    public String queryTopicProduct(TopicProductQry qry) {
        return new SQL() {
            {
                SELECT("gs.id as spuId," +
                        "gs.name AS name," +
                        "gs.sketch AS sketch," +
                        "gs.category_id AS categoryId," +
                        "gs.shop_id AS shopId," +
                        "gs.tag AS tag," +
                        "gs.picture_url AS pictureUrl," +
                        "gs.spu_code AS spuCode," +
                        "gs.price AS price," +
                        "gs.market_price AS marketPrice," +
                        "gs.stock AS stock," +
                        "gs.virtual_sales AS virtualSales," +
                        "gs.real_sales AS realSales," +
                        "gtp.sort AS sort," +
                        "gs.is_hot AS isHot," +
                        "gs.is_dis AS isDis," +
                        "gs.goods_status AS goodsStatus," +
                        "gs.poster AS poster," +
                        "gtp.id AS id"
                );
                FROM("goods_topic_product gtp");
                LEFT_OUTER_JOIN("goods_spu gs ON gtp.spu_id=gs.id");
                WHERE("gs.status = 1 ");
                WHERE("gtp.status = 1 AND gtp.topic_id = " + qry.getTopicId());
                ORDER_BY("gtp.sort DESC, gtp.update_date DESC");
            }
        }.toString();
    }

    /**
     * 根据主题查询未选中的产品（后台）
     *
     * @param qry
     * @return
     */
    public String queryNotYetProductByFront(TopicProductPageQry qry) {
        return new SQL() {
            {
                SELECT("gs.id as spuId," +
                        "gs.name AS name," +
                        "gs.sketch AS sketch," +
                        "gs.category_id AS categoryId," +
                        "gs.shop_id AS shopId," +
                        "gs.tag AS tag," +
                        "gs.picture_url AS pictureUrl," +
                        "gs.spu_code AS spuCode," +
                        "gs.price AS price," +
                        "gs.market_price AS marketPrice," +
                        "gs.stock AS stock," +
                        "gs.virtual_sales AS virtualSales," +
                        "gs.real_sales AS realSales," +
                        "gs.is_hot AS isHot," +
                        "gs.is_dis AS isDis," +
                        "gs.goods_status AS goodsStatus," +
                        "gs.poster AS poster"
                );
                FROM("goods_spu gs");
                WHERE("gs.status = 1");
                WHERE("gs.goods_status = 0");
                if (StringUtils.isNotBlank(qry.getName())) {
                    WHERE(" gs.name like '%" + qry.getName() + "%' ");
                }
                if (null != qry.getCategoryId()) {
                    WHERE("gs.category_id  like '%" + qry.getCategoryId() + "%'");
                }
                WHERE("gs.id not in (SELECT spu_id FROM goods_topic_product WHERE status=1 AND topic_id=" + qry.getTopicId() + ")");
                ORDER_BY("gs.sort DESC,gs.update_date DESC");
            }
        }.toString();
    }

    /**
     * 根据主题查询已选中的产品（前台）
     *
     * @param qry
     * @return
     */
    public String queryTopicProductByFront(TopicProductQry qry) {
        return new SQL() {
            {
                SELECT("gs.id as spuId," +
                        "gs.name AS name," +
                        "gs.sketch AS sketch," +
                        "gs.category_id AS categoryId," +
                        "gs.shop_id AS shopId," +
                        "gs.tag AS tag," +
                        "gs.picture_url AS pictureUrl," +
                        "gs.spu_code AS spuCode," +
                        "gs.price AS price," +
                        "gs.market_price AS marketPrice," +
                        "gs.stock AS stock," +
                        "gs.virtual_sales AS virtualSales," +
                        "gs.real_sales AS realSales," +
                        "gtp.sort AS sort," +
                        "gs.is_hot AS isHot," +
                        "gs.is_dis AS isDis," +
                        "gs.goods_status AS goodsStatus," +
                        "gs.poster AS poster," +
                        "gtp.id AS id"
                );
                FROM("goods_topic_product gtp");
                LEFT_OUTER_JOIN("goods_spu gs ON gtp.spu_id=gs.id");
                LEFT_OUTER_JOIN("goods_spu_other gso ON gs.id = gso.spu_id");
                WHERE("now() > gso.up_time");
                WHERE("now() < gso.down_time");
                WHERE("gs.goods_status in(0)");
                WHERE("gs.status = 1 AND gs.goods_status = 0");
                WHERE("gtp.topic_id = " + qry.getTopicId());
                WHERE("gtp.status = 1");
                if (StringUtils.isNotBlank(qry.getName())) {
                    WHERE(" gs.name like '%" + qry.getName() + "%' ");
                }
                if (null != qry.getCategoryId()) {
                    WHERE("gs.category_id  like '%" + qry.getCategoryId() + "%'");
                }
                ORDER_BY("gtp.sort DESC, gtp.update_date DESC");
            }
        }.toString();
    }


    /**
     * 查询我名下的可用的优惠券
     */
    public String queryMyCouponSql(CouponReceiveQry couponQry) {
        return new SQL() {
            {
                SELECT("cr.id," +
                        "cr.coupon_id," +
                        "cr.mem_id," +
                        "cr.coupon_code," +
                        "cr.receive_time," +
                        "cr.use_time," +
                        "cr.use_status," +
                        "cr.status," +
                        "cr.version," +
                        "cr.remarks," +
                        "cr.create_date," +
                        "cr.update_date");
                FROM(" coupon c, coupon_receive cr ");
                WHERE(
                        " c.status=1 AND cr.status=1 AND c.id = cr.coupon_id AND cr.use_status=0"
                );
                WHERE("cr.mem_id = " + couponQry.getMemId());
                if (null != couponQry.getAmount() && couponQry.getAmount().compareTo(new BigDecimal(0)) == 1) {
                    WHERE("((c.type = 2 AND c.amount <= " + couponQry.getAmount() + " ) OR c.type != 2 )");
                }

                if (null != couponQry.getCouponIds() && couponQry.getCouponIds().size() > 0) {
                    String ids = "(";
                    for (Long id : couponQry.getCouponIds()) {
                        ids += id + ",";
                    }
                    ids = ids.substring(0, ids.length() - 1);
                    ids = ids + ")";
                    WHERE(" ( c.scope_type=1 or (c.scope_type=2 and c.id in " + ids + " ) or (c.scope_type = 3 and c.id not in" + ids + ")) ");
                }
                ORDER_BY("cr.create_date DESC");
            }

        }.toString();
    }


    /**
     * 查询广告位广告
     *
     * @param advertisement
     * @return
     */
    public String queryAd(Advertisement advertisement) {
        return new SQL() {
            {
                SELECT("*");
                FROM(" cms_ad ");
                WHERE(" status=1 AND ad_position_id=#{adPositionId.id} AND is_enabled=0 ");
                if (StringUtils.isNotBlank(advertisement.getAreaNames())) {
                    WHERE(" area_names LIKE '%" + advertisement.getAreaNames() + "%'");
                }
                ORDER_BY("sort DESC");
            }
        }.toString();
    }


    /**
     * 根据商品参数查询产品
     *
     * @param spuQry
     * @return
     */
    public String querySpuWithParam(SpuQry spuQry) {
        return new SQL() {
            {
                SELECT(" s.id");
                FROM(" goods_spu s,goods_spu_parameter sp ");
                WHERE(" s.status = 1  AND sp.status = 1 AND s.id=sp.spu_id ");
                if (null != spuQry.getParamValueIds() && spuQry.getParamValueIds().size() > 0) {
                    String paramValueIds = "(";
                    for (Long paramValueId : spuQry.getParamValueIds()) {
                        paramValueIds += paramValueId + ",";
                    }
                    paramValueIds = paramValueIds.substring(0, paramValueIds.length() - 1);
                    paramValueIds = paramValueIds + ")";
                    WHERE(" sp.parameter_value_id IN " + paramValueIds);
                }
                GROUP_BY("s.id");
                HAVING("count(s.id) = " + spuQry.getParamValueIds().size());
            }
        }.toString();
    }
}
