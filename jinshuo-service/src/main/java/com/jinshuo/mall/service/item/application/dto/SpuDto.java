package com.jinshuo.mall.service.item.application.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.jinshuo.mall.domain.item.category.model.Category;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Classname SpuDto
 * @Description TODO
 * @Date 2019/6/28 9:29
 * @Created by dyh
 */
@Data
@Accessors(chain = true)
public class SpuDto implements Serializable {

    @ApiModelProperty(value = "spuId")
    private String id;

    @ApiModelProperty(value = "商品名称")
    private String name;

    @ApiModelProperty(value = "供应商id")
    private String supplierId;

    @ApiModelProperty(value = "供应商id")
    private String supplierName;

    @ApiModelProperty(value = "商品简述")
    private String sketch;

    @ApiModelProperty(value = "商品分类编号")
    private List<String> categoryId;

    @ApiModelProperty(value = "商品分类名称")
    private String categoryName;

    @ApiModelProperty(value = "店铺编号")
    private String shopId;

    @ApiModelProperty(value = "类型编号")
    private String typeId;

    @ApiModelProperty(value = "品牌编号")
    private String brandId;

    @ApiModelProperty(value = "商品分组编号")
    private String groudId;

    @ApiModelProperty(value = "商品标签")
    private String tag;

    @ApiModelProperty(value = "封面图")
    private String pictureUrl;

    @ApiModelProperty(value = "SPU编码")
    private String spuCode;

    @ApiModelProperty(value = "单位")
    private String unit;

    @ApiModelProperty(value = "销售价")
    private BigDecimal price;

    @ApiModelProperty(value = "市场价")
    private BigDecimal marketPrice;

    @ApiModelProperty(value = "成本价")
    private BigDecimal costPrice;

    @ApiModelProperty(value = "库存量")
    private Integer stock;

    @ApiModelProperty(value = "库存警告")
    private Integer warningStock;

    @ApiModelProperty(value = "初始销量")
    private Integer virtualSales;

    @ApiModelProperty(value = "实际销量")
    private Integer realSales;

    @ApiModelProperty(value = "是否积分产品")
    private Integer isIntegral;

    @ApiModelProperty(value = "可使用积分抵消")
    private Integer integral;

    @ApiModelProperty(value = "排序")
    private Integer sort;

    @ApiModelProperty(value = "产品状态")
    private Integer goodsStatus;

    @ApiModelProperty(value = "审核状态")
    private Integer auditStatus;

    @ApiModelProperty(value = "skus")
    private List<SkuDto> skus;

    @ApiModelProperty(value = "是否热门产品")
    private Integer isHot;

    @ApiModelProperty(value = "标签集")
    private List<String> tags;

    @ApiModelProperty(value = "标签集")
    private List<SpuTagDto> tagDto;

    @ApiModelProperty(value = "其他设置")
    private SpuOtherDto spuOtherDto;

    @ApiModelProperty(value = "图片链接")
    private List<String> urls;

    @ApiModelProperty(value = "是否发码 0是 1不是")
    private Integer isSendcode;

    @ApiModelProperty(value = "预约地址")
    private String reserveAddress;

    @ApiModelProperty(value = "预约地址")
    private String poster;

    @ApiModelProperty(value = "产品属性id")
    private String featureId;

    @ApiModelProperty(value = "支持服务")
    private String services;

    @ApiModelProperty(value = "支持服务")
    private String servicesName;

    @ApiModelProperty(value = "地区")
    private String areaName;

    @ApiModelProperty(value = "地区s")
    private List<String> areaNames;

    @ApiModelProperty(value = "添加时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createDate;

    /**
     * 是否分销商商品 0是 1否
     */
    private Integer isDis;

    /**
     * 是否自主发码
     */
    private Integer autoSendCode;

    private List<ParameterDto> params;

    public SpuDto changeCategoryName(Category category) {
        if (null != category) {
            this.categoryName = category.getName();
        }
        return this;
    }


    public SpuDto changeTags(List<SpuTagDto> dtos) {
        if (null != dtos) {
            this.tagDto = dtos;
            List<String> strs = new ArrayList<>();
            dtos.forEach(spuTagDto -> strs.add(spuTagDto.getTagId()));
            this.tags = strs;
        }
        return this;
    }

    public SpuDto changeSkus(List<SkuDto> dtos) {
        if (null != dtos) {
            this.skus = dtos;
        }
        return this;
    }

    public SpuDto changeSpuOtherDto(SpuOtherDto dto) {
        if (null != dto) {
            this.spuOtherDto = dto;
        }
        return this;
    }

    public SpuDto changeUrl(List<String> urls) {
        if (null != urls) {
            this.urls = urls;
        }
        return this;
    }

    /*public SpuDto changeSupplierName(SupplierDto dto) {
        if (null != dto && null != dto.getSupplierName()) {
            this.supplierName = dto.getSupplierName();
        }
        this.autoSendCode = dto.getLoginFlag();
        return this;
    }*/

}
