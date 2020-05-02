package com.jinshuo.core.support;

import com.jinshuo.core.response.WrapperResponse;
import lombok.extern.slf4j.Slf4j;

/**
 * @Classname BaseController
 * @Description TODO
 * @Date 2019/6/23 17:28
 * @Created by dyh
 */
@Slf4j
public class BaseController {



    protected WrapperResponse formatErrorResponse(final Exception e) {
        return WrapperResponse.fail(e.getMessage());

    }

    protected WrapperResponse formatSuccessResponse() {
        return WrapperResponse.success();

    }

   /* protected <E> WrapperResponse<E> handleResult(E result, String errorMsg) {
        boolean flag = isFlag(result);

        if (flag) {
            return WrapperResponse.success(result);
        } else {
            return WrapperResponse.fail( errorMsg);
        }
    }*/

  /*  private boolean isFlag(Object result) {
        boolean flag;
        if (result instanceof Integer) {
            flag = (Integer) result > 0;
        } else if (result instanceof Boolean) {
            flag = (Boolean) result;
        } else {
            flag = PublicUtil.isNotEmpty(result);
        }
        return flag;
    }*/
}
