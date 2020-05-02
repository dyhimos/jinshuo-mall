package com.jinshuo.mall.domain.user.model.scoreRecord;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.jinshuo.core.model.enums.BaseEnum;

/**
* @Description:    积分类型
* @Author:         dongyh
* @CreateDate:     2019/7/23 16:43
* @UpdateUser:     dongyh
* @UpdateDate:     2019/7/23 16:43
* @UpdateRemark:
* @Version:        1.0
*/
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum ScoreTypeEnums implements BaseEnum<ScoreTypeEnums,Integer> {
  TYPE_ADD(1,"增加"),
  TYPE_COST(2,"消费")
;
  public Integer value;
  public String displayName;

  private ScoreTypeEnums(Integer value, String displayName){
    this.value = value;
    this.displayName = displayName;
  }

  @Override
  public Integer getValue() {
    return value;
  }

  @Override
  public String getDisplayName() {
    return displayName;
  }
}
