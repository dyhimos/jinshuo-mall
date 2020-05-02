package com.jinshuo.mall.service.user.application.assermbler;

import com.jinshuo.mall.domain.user.model.userAccountPlatform.UserAccountPlatform;
import com.jinshuo.mall.service.user.application.dto.UserAccountPlatformDto;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

/**
 * @Classname UserAccountAssembler
 * @Description UserAccountAssembler模块的组装器，完成domain model对象到dto的转换，组装职责包括：
 * 1、完成类型转换、数据格式化；如日志格式化，状态enum装换为前端认识的string；
 * 2、将多个model组合成一个dto，一并返回。
 * @Date 2019/6/17 18:27
 * @Created by dongyh
 */
@Component
public class UserAccountAssembler {

    /**
     *转化用户信息
     * @param userAccountPlatform
     * @return
     */
    public static UserAccountPlatformDto assembleUserAccount(UserAccountPlatform userAccountPlatform) {
        UserAccountPlatformDto userAccountPlatformDto = new UserAccountPlatformDto();
        BeanUtils.copyProperties(userAccountPlatform, userAccountPlatformDto);
        return userAccountPlatformDto;
    }
}
