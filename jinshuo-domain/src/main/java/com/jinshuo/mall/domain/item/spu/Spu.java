package com.jinshuo.mall.domain.item.spu;

import com.jinshuo.core.exception.item.IcBizException;
import com.jinshuo.core.exception.item.IcReturnCode;
import com.jinshuo.core.model.IdentifiedEntity;
import com.jinshuo.core.utils.UrlUtil;
import com.jinshuo.mall.domain.item.brand.BrandId;
import com.jinshuo.mall.domain.item.group.GroupId;
import com.jinshuo.mall.domain.item.shop.ShopId;
import com.jinshuo.mall.domain.item.spu.valueobject.SpuId;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @Classname Spu
 * @Description TODO
 * @Date 2019/6/16 20:01
 * @Created by dyh
 */
@Slf4j
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Spu extends IdentifiedEntity {//实体，同时也是聚合根

    private SpuId spuId;

    /**
     * 供应商id
     */
    private Long supplierId;

    /**
     * 商品名称
     */
    private String name;

    /**
     * 商品简述
     */
    private String sketch;

    /**
     * 商品分类编号
     */
    private String categoryId;

    /**
     * 商家id
     */
    private Long merchantId;

    /**
     * 店铺编号
     */
    private ShopId shopId;

    /**
     * 类型编号
     */
    private Long typeId;

    /**
     * 品牌编号
     */
    private BrandId brandId;

    /**
     * 商品分组编号
     */
    private GroupId groudId;

    /**
     * 商品标签
     */
    private String tag;

    /**
     * 封面图
     */
    private String pictureUrl;

    /**
     * SPU编码
     */
    private String spuCode;

    /**
     * 单位
     */
    private String unit;

    /**
     * 销售价
     */
    private BigDecimal price;

    /**
     * 市场价
     */
    private BigDecimal marketPrice;

    /**
     * 成本价
     */
    private BigDecimal costPrice;

    /**
     * 库存量(库存量为 sku的库存加起来 减去初始销量，所以录产品的时候要计算好)
     */
    private Integer stock;

    /**
     * 警告库存
     */
    private Integer warningStock;

    /**
     * 设置后，您的用户看到的销量=初始销量+下单量
     */
    private Integer virtualSales;

    /**
     * 实际销量
     */
    private Integer realSales;

    /**
     * 是否积分产品
     */
    private Integer isIntegral;

    /**
     * 可使用积分抵消
     */
    private Integer integral;

    /**
     * 排序
     */
    private Integer sort;

    /**
     * 产品状态 状态 1=>下架,0=>上架,2=>预售,3=>售罄,4=>未上架
     */
    private Integer goodsStatus;

    /**
     * 审核状态 -1 审核失败 0 未审核 1 审核成功
     */
    private Integer auditStatus;

    /**
     * 是否热门产品
     */
    private Integer isHot;

    /**
     * 是否分销商商品 0是 1否
     */
    private Integer isDis;

    /**
     * 是否发码 0是 1不是
     */
    private Integer isSendcode;

    /**
     * 预约地址
     */
    private String reserveAddress;

    /**
     * 海报
     */
    private String poster;

    /**
     * 支持服务
     */
    private String services;

    /**
     * 是否单规格 0是 1否
     */
    private Integer singleSku;

    @ApiModelProperty(value = "产品属性")
    private Long featureId;


    /**
     * 地区
     */
    private String areaName;

    /**
     * 地区
     */
    private String areaNames;


    public void update() {
        this.updateDate = new Date();
    }

    public Spu cutStock(Integer stocks) {
        checkGoodStatus();
        if (this.stock < stocks) {
            throw new IcBizException(IcReturnCode.IC201008.getCode(), IcReturnCode.IC201008.getMsg());
        }
        this.stock = this.stock - stocks;
        this.virtualSales = this.virtualSales + stocks;
        this.realSales = this.realSales + stocks;
        if (1 > this.stock) {
            this.goodsStatus = 3;
        }
        return this;
    }

    public Spu checkStock(Integer stocks) {
        checkGoodStatus();
        if (this.stock < stocks) {
            throw new IcBizException(IcReturnCode.IC201008.getCode(), IcReturnCode.IC201008.getMsg());
        }
        return this;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = UrlUtil.getUrl(pictureUrl);
    }

    public void checkGoodStatus() {
        //产品状态 状态 1=>下架,0=>上架,2=>预售,3=>售罄,4=>未上架
        if (1 == this.goodsStatus) {
            throw new IcBizException(IcReturnCode.IC201020.getCode(), IcReturnCode.IC201020.getMsg());
        }
        if (3 == this.goodsStatus) {
            throw new IcBizException(IcReturnCode.IC201023.getCode(), IcReturnCode.IC201023.getMsg());
        }
        if (0 != this.goodsStatus) {
            throw new IcBizException(IcReturnCode.IC201024.getCode(), IcReturnCode.IC201024.getMsg());
        }
    }
}
