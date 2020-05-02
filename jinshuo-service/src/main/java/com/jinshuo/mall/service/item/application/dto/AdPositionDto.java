package com.jinshuo.mall.service.item.application.dto;

import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * Created by 19458 on 2019/9/10.
 */
@Data
public class AdPositionDto {
    private String id;
    private Integer isCycle; //是否轮循 0是 1不是
    private String name;
    private String desc;
    private String code;
    private String shopId;
    private Date startTime; //广告开始时间
    private Date endTime; //广告结束时间
    private Integer showType; //展示类型 0每次显示 1间隔显示
    private String gapTime; //间隔时间
    private String areaIds; //地区
    private Integer memberType; //会员类型
    private List<AdvertisementDto> list;
    private Boolean popUpFlag = false;
}
