package com.jinshuo.mall.domain.item.sku;

import com.jinshuo.core.exception.item.IcBizException;
import com.jinshuo.core.exception.item.IcReturnCode;
import com.jinshuo.core.model.IdentifiedEntity;
import com.jinshuo.core.utils.UrlUtil;
import com.jinshuo.mall.domain.item.spu.valueobject.SpuId;
import lombok.*;
import lombok.experimental.Accessors;

import java.math.BigDecimal;

/**
 * Created by dongyh on 2019/7/17.
 */
@Data
@ToString(callSuper = true)
@Getter
@Setter(AccessLevel.PROTECTED)
@Accessors(chain = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Sku extends IdentifiedEntity {

    /**
     * skuId
     */
    private SkuId skuId;

    /**
     * spuId
     */
    private SpuId spuId;

    /**
     * sku名称
     */
    private String name;

    /**
     * 主图
     */
    private String pictureUrl;

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
     * 库存量
     */
    private Integer stock;

    /**
     * 销量
     */
    private Integer salesQuantity;

    /**
     * sku编码
     */
    private String skuCode;

    /**
     * 条形码
     */
    private String barCode;


    public Sku(SkuId skuId, SpuId spuId, String name, String pictureUrl, BigDecimal price, BigDecimal marketPrice, BigDecimal costPrice, Integer stock, Integer salesQuantity) {
        this.skuId = skuId;
        this.spuId = spuId;
        this.name = name;
        this.pictureUrl = pictureUrl;
        this.price = price;
        this.marketPrice = marketPrice;
        this.costPrice = costPrice;
        this.stock = stock;
        this.salesQuantity = salesQuantity;
    }

    public Sku cutStock(Integer stocks) {
        if (this.stock < stocks) {
            throw new IcBizException(IcReturnCode.IC201008.getCode(), IcReturnCode.IC201008.getMsg());
        }
        this.stock = this.stock - stocks;
        this.salesQuantity = this.salesQuantity + stocks;
        return this;
    }

    public Sku checkStock(Integer stocks) {
        if (this.stock < stocks) {
            throw new IcBizException(IcReturnCode.IC201008.getCode(), IcReturnCode.IC201008.getMsg());
        }
        return this;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = UrlUtil.getUrl(pictureUrl);
    }
}
