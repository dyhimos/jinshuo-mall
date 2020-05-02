package com.jinshuo.mall.service.item.application.dto;

import com.jinshuo.mall.domain.item.category.model.Category;
import com.jinshuo.mall.domain.item.feature.Feature;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @Classname SpuDto
 * @Description TODO
 * @Date 2019/6/28 9:29
 * @Created by dyh
 */
@Slf4j
@Data
@Accessors(chain = true)
public class UserSpuDto implements Serializable {

    @ApiModelProperty(value = "spuId")
    private String id;

    @ApiModelProperty(value = "商品名称")
    private String name;

    @ApiModelProperty(value = "商品简述")
    private String sketch;

    @ApiModelProperty(value = "供应商id")
    private String supplierId;

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

    @ApiModelProperty(value = "库存量")
    private Integer stock;

    @ApiModelProperty(value = "初始销量")
    private Integer virtualSales;

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
    private List<UserSkuDto> skus;

    @ApiModelProperty(value = "是否抢购商品")
    private Integer isScareBuy;

    @ApiModelProperty(value = "标签集")
    private List<String> tags;

    @ApiModelProperty(value = "是否热门")
    private Integer isHot;

    @ApiModelProperty(value = "标签集")
    private List<SpuTagDto> tagDto;

    @ApiModelProperty(value = "其他设置")
    private SpuOtherDto spuOtherDto;

    @ApiModelProperty(value = "详细信息")
    private SpuDescDto spuDescDto;

    @ApiModelProperty(value = "图片链接")
    private List<String> urls;

    @ApiModelProperty(value = "产品属性id")
    private String featureId;

    @ApiModelProperty(value = "产品属性名称")
    private String featureName;

    @ApiModelProperty(value = "佣金")
    private BigDecimal disMoney;

    @ApiModelProperty(value = "支持服务")
    private String services;

    @ApiModelProperty(value = "支持服务")
    private String servicesName;

    private List<String> areaNames;

    private List<ParameterDto> params;


    public UserSpuDto changeUrl(List<String> urls) {
        if (null != urls) {
            this.urls = urls;
        }
        return this;
    }


    public UserSpuDto changeSkus(List<UserSkuDto> dtos) {
        if (null != dtos) {
            this.skus = dtos;
        }
        return this;
    }

    public UserSpuDto changeCategoryName(Category category) {
        if (null != category) {
            this.categoryName = category.getName();
        }
        return this;
    }

    public UserSpuDto changeTags(List<SpuTagDto> dtos) {
        if (null != dtos) {
            this.tagDto = dtos;
            List<String> strs = new ArrayList<>();
            dtos.forEach(spuTagDto -> strs.add(spuTagDto.getTagId()));
            this.tags = strs;
        }
        return this;
    }

    public UserSpuDto changeSpuOtherDto(SpuOtherDto dto) {
        if (null != dto) {
            this.spuOtherDto = dto;
        }
        return this;
    }

    public UserSpuDto changeSpuDescDto(SpuDescDto dto) {
        if (null != dto) {
            this.spuDescDto = dto;
        }
        return this;
    }

    public UserSpuDto changeFeatrue(Feature feature){
        if(null==feature||null==feature.getName()){
            return this;
        }
        this.featureName  = feature.getName();
        return this;
    }
}
