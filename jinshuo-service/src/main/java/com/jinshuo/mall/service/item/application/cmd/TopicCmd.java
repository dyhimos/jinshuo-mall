package com.jinshuo.mall.service.item.application.cmd;

import com.jinshuo.core.exception.item.IcBizException;
import com.jinshuo.core.exception.item.IcReturnCode;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * Created by 19458 on 2019/9/29.
 */
@Data
public class TopicCmd {
    private Long id;

    @ApiModelProperty(value = "活动类型 0活动 1主题")
    @NotNull(message = "活动类型不能为空")
    private Integer type;

    @ApiModelProperty(value = "活动父代码")
    //@NotNull(message = "活动父代码不能为空")
    private Long pid;

    @ApiModelProperty(value = "展示样式")
    private Integer showType;

    @ApiModelProperty(value = "排序")
    private Integer sort;

    @ApiModelProperty(value = "活动代码")
    //@NotNull(message = "活动代码不能为空")
    private String code;

    @ApiModelProperty(value = "活动名称")
    @NotNull(message = "活动名称不能为空")
    private String name;

    @ApiModelProperty(value = "活动开始时间")
    //@NotNull(message = "活动开始时间不能为空")
    private Date startTime;

    @ApiModelProperty(value = "活动结束时间")
    //@NotNull(message = "活动结束时间不能为空")
    private Date endTime;

    @ApiModelProperty(value = "活动状态")
    //@NotNull(message = "活动状态不能为空")
    private Integer topicStatus;

    @ApiModelProperty(value = "活动描述")
    //@NotNull(message = "活动描述不能为空")
    private String topicDesc;

    @ApiModelProperty(value = "主图标")
    //@NotNull(message = "主图标不能为空")
    private String mainPicture;

    @ApiModelProperty(value = "小图标")
    //@NotNull(message = "小图标不能为空")
    private String signPicture;

    @ApiModelProperty(value = "海报")
    //@NotNull(message = "海报不能为空")
    private String posterPicture;

    @ApiModelProperty(value = "活动背景颜色")
    //@NotNull(message = "活动背景颜色不能为空")
    private String color;

    @ApiModelProperty(value = "店铺id")
    //@NotNull(message = "店铺id")
    private Long shopId;

    @ApiModelProperty(value = "0文字 1：图标")
    private Integer headingShowFlag; //

    @ApiModelProperty(value = "字体颜色")
    private String headingColor; //

    public void check() {
        if (0 == this.type) {
            if (null == this.startTime || null == this.endTime || StringUtils.isBlank(this.mainPicture) || StringUtils.isBlank(this.code)) {
                throw new IcBizException(IcReturnCode.IC201009.getCode(), IcReturnCode.IC201009.getMsg());
            }
        } else {
            if (null == this.pid || null == this.showType) {
                throw new IcBizException(IcReturnCode.IC201009.getCode(), IcReturnCode.IC201009.getMsg());
            }
        }
    }
}
