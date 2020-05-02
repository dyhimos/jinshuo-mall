package com.jinshuo.mall.service.item.application.cmd;

import com.jinshuo.mall.service.item.application.dto.ParameterDto;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * @Classname SpuCreateCmd
 * @Description TODO
 * @Date 2019/6/27 15:36
 * @Created by dyh
 */

@Data
@Accessors(chain = true)
public class SpuUpdateCmd implements Serializable {

    @ApiModelProperty(value = "商品id")
    private Long id;

    @ApiModelProperty(value = "供应商id")
    private Long supplierId;

    @ApiModelProperty(value = "名称")
    @NotNull(message = "名称不可为空")
    private String name;

    @ApiModelProperty(value = "商品简述")
    @NotNull(message = "商品简述不能为空!")
    private String sketch;

    @ApiModelProperty(value = "类型")
    @NotNull(message = "类型不能为空!")
    private List<String> categoryId;

    @ApiModelProperty(value = "商家")
    //@NotNull(message = "商家不能为空!")
    private Long shopId;

    @ApiModelProperty(value = "类型")
    @NotNull(message = "类型不能为空!")
    private Long typeId;

    @ApiModelProperty(value = "品牌")
    //@NotNull(message = "品牌不能为空!")
    private Long brandId;

    @ApiModelProperty(value = "商品分组编号")
    //@NotNull(message = "商品分组编号不能为空!")
    private Long groudId;

    @ApiModelProperty(value = "商品标签")
    //@NotNull(message = "商品标签不能为空!")
    private String tag;

    @ApiModelProperty(value = "封面图")
    @NotNull(message = "封面图不能为空!")
    private String pictureUrl;

    @ApiModelProperty(value = "SPU编码")
    @NotNull(message = "SPU编码不能为空!")
    private String spuCode;

    @ApiModelProperty(value = "单位")
    @NotNull(message = "单位不能为空!")
    private String unit;

    @ApiModelProperty(value = "销售价")
    @NotNull(message = "销售价简述不能为空!")
    private BigDecimal price;

    @ApiModelProperty(value = "市场价")
    @NotNull(message = "市场价不能为空!")
    private BigDecimal marketPrice;

    @ApiModelProperty(value = "成本价")
    @NotNull(message = "成本价不能为空!")
    private BigDecimal costPrice;

    @ApiModelProperty(value = "库存")
    @NotNull(message = "库存不能为空!")
    private Integer stock;

    @ApiModelProperty(value = "库存警告")
    @NotNull(message = "库存警告不能为空!")
    private Integer warningStock;

    @ApiModelProperty(value = "初始销量")
    @NotNull(message = "初始销量不能为空!")
    private Integer virtualSales;

    @ApiModelProperty(value = "是否积分产品")
    @NotNull(message = "是否积分产品不能为空!")
    private Integer isIntegral;

    @ApiModelProperty(value = "可使用积分抵消")
    //@NotNull(message = "可使用积分抵消不能为空!")
    private Integer integral;

    @ApiModelProperty(value = "商品排序")
    //@NotNull(message = "商品排序不能为空!")
    private Integer sort;

    @ApiModelProperty(value = "产品状态")
    @NotNull(message = "产品状态不能为空!")
    private Integer goodsStatus;

    @ApiModelProperty(value = "审核状态")
    //@NotNull(message = "审核状态不能为空!")
    private Integer auditStatus;

    @ApiModelProperty(value = "规格集合")
    //@NotNull(message = "规格集合不能为空!")
    private List<SkuCreateCmd> skus;

    @ApiModelProperty(value = "是否热门")
    //@NotNull(message = "是否热门商品不能为空!")
    private Integer isHot;

    @ApiModelProperty(value = "商标集合")
    //@NotNull(message = "商标集合不能为空!")
    private List<Long> tags;

    @ApiModelProperty(value = "图片集合")
    //@NotNull(message = "商标集合不能为空!")
    private List<String> urls;

    @ApiModelProperty(value = "是否发码 0是 1不是")
    private Integer isSendcode;

    @ApiModelProperty(value = "预约地址")
    private String reserveAddress;

    @ApiModelProperty(value = "产品海报")
    private String poster;

    @ApiModelProperty(value = "产品属性")
    private Long featureId;

    @ApiModelProperty(value = "支持服务")
    private String services;

    @ApiModelProperty(value = "是否单规格 0是 1否")
    private Integer singleSku = 1;

    /*@ApiModelProperty(value = "地区s")
    private List<String> areaNames;*/

    @ApiModelProperty(value = "产品属性")
    private List<ParameterDto> params;

}
