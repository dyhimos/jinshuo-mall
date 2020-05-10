package com.jinshuo.mall.service.user.service.command;

import com.alibaba.fastjson.JSONObject;
import com.jinshuo.core.exception.user.UcBizException;
import com.jinshuo.core.exception.user.UcReturnCode;
import com.jinshuo.core.idgen.CommonSelfIdGenerator;
import com.jinshuo.core.utils.BPwdEncoderUtils;
import com.jinshuo.core.utils.UserIdUtils;
import com.jinshuo.mall.domain.user.model.supplier.SupplierManager;
import com.jinshuo.mall.domain.user.model.userAccount.UserAccount;
import com.jinshuo.mall.domain.user.model.userAccount.UserAccountId;
import com.jinshuo.mall.domain.user.model.userAccount.UserAccountStatusEnums;
import com.jinshuo.mall.service.user.application.assermbler.SupplierManagerAssembler;
import com.jinshuo.mall.service.user.application.cmd.SupplierManagerCmd;
import com.jinshuo.mall.service.user.application.dto.SupplierDto;
import com.jinshuo.mall.service.user.application.dto.SupplierManagerDto;
import com.jinshuo.mall.service.user.mybatis.SupplierManagerRepo;
import com.jinshuo.mall.service.user.mybatis.UserAccountRepo;
import com.jinshuo.mall.service.user.service.query.SupplierManagerQueryService;
import com.jinshuo.mall.service.user.service.query.SupplierQueryService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by 19458 on 2019/7/22.
 */
@Slf4j
@Service
public class SupplierManagerComService {

    @Autowired
    private SupplierManagerRepo supplierManagerRepo;

    @Autowired
    private SupplierQueryService supplierQueryService;

    @Autowired
    private SupplierManagerQueryService supplierManagerQueryService;

    @Autowired
    private UserAccountCmdService userAccountCmdService;

    @Autowired
    private UserAccountRepo userAccountRepo;


    /**
     * 保存
     *
     * @param cmd
     */
    public int save(SupplierManagerCmd cmd) {
        log.info(" -- 保存供应商管理员，输入参数{}", cmd);
        SupplierManager supplierManager = new SupplierManager();
        if (null == cmd.getId()) {
            cmd.setId(CommonSelfIdGenerator.generateId());
            BeanUtils.copyProperties(cmd, supplierManager);
            supplierManager.insert();
            UserAccount userAccount = buildUser(cmd);
            supplierManager.setUserAccountId(userAccount.getUserAccountId().getId());
            List<UserAccount> list = checkUserName(cmd.getMobile(), 4);
            if (null != list && list.size() > 0) {
                throw new UcBizException(UcReturnCode.UC200030.getMsg(), UcReturnCode.UC200030.getCode());
            }
            userAccountCmdService.saveUser(userAccount);
            return supplierManagerRepo.save(supplierManager);
        } else {
            supplierManager = supplierManagerQueryService.getById(cmd.getId());
            if (null == supplierManager) {
                throw new UcBizException(UcReturnCode.UC200021.getMsg(), UcReturnCode.UC200021.getCode());
            }
            supplierManager.update(cmd.getName(), cmd.getMobile(), cmd.getIdCard(), cmd.getRole(), cmd.getSex(), cmd.getSupplierManagerStatus());
            UserAccount userAccount = userAccountRepo.findById(new UserAccountId(supplierManager.getUserAccountId()));
            log.info(" -- " + cmd.getMobile() + JSONObject.toJSONString(cmd));
            List<UserAccount> list = checkUserName(cmd.getMobile(), 4);
            if (list.size() > 0) {
                if (list.size() == 1) {
                    if ((list.get(0).getUserAccountId().getId().longValue()) != userAccount.getUserAccountId().getId().longValue()) {
                        throw new UcBizException(UcReturnCode.UC200030.getMsg(), UcReturnCode.UC200030.getCode());
                    }
                } else {
                    throw new UcBizException(UcReturnCode.UC200030.getMsg(), UcReturnCode.UC200030.getCode());
                }
            }
            userAccount.setUsername(cmd.getMobile());
            userAccount.setNickname(cmd.getName());
            if (StringUtils.isNotBlank(cmd.getPassword())) {
                userAccount.setPassword(BPwdEncoderUtils.BCryptPassword(cmd.getPassword()));
            }
            userAccountCmdService.updateUser(userAccount);
            return supplierManagerRepo.update(supplierManager);
        }
    }

    /**
     * 删除
     *
     * @param id
     */
    public int delete(Long id) {
        return supplierManagerRepo.delete(id);
    }


    /**
     * 根据登录id查询供应商核销员信息
     */
    public SupplierManagerDto getInfoByAccountId(Long id) {
        SupplierManager supplierManager = supplierManagerRepo.queryByUserAccountId(id);
        SupplierDto supplier = supplierQueryService.getById(supplierManager.getSupplierId());
        SupplierManagerDto dto = SupplierManagerAssembler.assembleDto(supplierManager);
        dto.setSupplierName(supplier.getSupplierName());
        return dto;
    }

    /**
     * 根据登录id查询供应商核销员信息
     */
    public SupplierManagerDto getInfoByToken() {
        Long id = UserIdUtils.getUserId();
        return getInfoByAccountId(id);
    }

    public UserAccount buildUser(SupplierManagerCmd cmd) {
        UserAccount userAccount = UserAccount.builder()
                .userAccountId(new UserAccountId(CommonSelfIdGenerator.generateId()))
                .nickname(cmd.getName())
                .username(cmd.getMobile())
                .password(BPwdEncoderUtils.BCryptPassword(cmd.getPassword()))
                .phone(cmd.getMobile())
                .type(4)
                .userStatus(UserAccountStatusEnums.ACCOUNT_ENABLE)
                .build();
        return userAccount;
    }

    public List<UserAccount> checkUserName(String username, Integer type) {
        return null;
    }

}
