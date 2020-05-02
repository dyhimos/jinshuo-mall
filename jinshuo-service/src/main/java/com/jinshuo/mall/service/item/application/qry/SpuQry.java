package com.jinshuo.mall.service.item.application.qry;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @Classname SpuQry
 * @Description TODO
 * @Date 2019/6/27 15:43
 * @Created by dyh
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SpuQry {

    @ApiModelProperty(value = "页码")
    private Integer pageNum = 0;

    @ApiModelProperty(value = "每页数量")
    private Integer pageSize = 10;

    @ApiModelProperty(value = "商品小类id")
    private Long skuId;

    @ApiModelProperty(value = "商品大类id")
    private Long spuId;

    @ApiModelProperty(value = "店铺id")
    private Long shopId;

    @ApiModelProperty(value = "品牌id")
    private Long brandId;

    @ApiModelProperty(value = "类目id")
    private Long categoryId;

    @ApiModelProperty(value = "商品状态 1：下架 0：销售中 2=>预售,3=>售罄,4=>未上架")
    private Integer goodsStatus;

    @ApiModelProperty(value = "商品名称")
    private String name;

    @ApiModelProperty(value = "商品名称")
    private String spuName;

    @ApiModelProperty(value = "标签")
    private List<Long> tags;

    @ApiModelProperty(value = "是否抢购商品")
    private Integer isScareBuy;

    @ApiModelProperty(value = "查询类型 1：最新开抢 2：开抢预告  3:已结束抢购  4:即将结束抢购")
    private String qryType;

    @ApiModelProperty(value = "是否热门")
    private Integer isHot;

    @ApiModelProperty(value = "排序类型 1：销售价 2：库存 3：销量 4：权重 5：添加时间")
    private Integer sortType;

    @ApiModelProperty(value = "是否升序 0->是 1(其他)->否")
    private Integer isAscending;

    @ApiModelProperty(value = "客户标识 1为客户查询")
    private Integer flag;

    @ApiModelProperty(value = "客户标识 1为客户查询")
    private Integer count;

    @ApiModelProperty(value = "参与->0 不参与->1(分销)")
    private Integer isDis;

    @ApiModelProperty(value = "产品标签属性")
    private Long featureId;

    /**
     * 供应商id
     */
    @ApiModelProperty(value = "供应商id")
    private Long supplierId;

    /**
     * 标签
     */
    @ApiModelProperty(value = "标签")
    private Long tag;

    @ApiModelProperty(value = "地区")
    private String areaName;

    @ApiModelProperty(value = "参数值id")
    private List<Long> paramValueIds;

    private String valueIds;

    public SpuQry setId(Integer type, Long targetId) {
        this.spuId = targetId;
        return this;
    }
}
