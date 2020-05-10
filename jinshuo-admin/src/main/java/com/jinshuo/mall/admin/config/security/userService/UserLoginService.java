package com.jinshuo.mall.admin.config.security.userService;

import com.jinshuo.mall.service.user.service.command.UserAccountCmdService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Created by pc on 2019/5/6.
 */
@Slf4j
@Service
public class UserLoginService implements UserDetailsService {

    @Autowired
    private UserAccountCmdService userAccountCmdService;

    @Override
    public org.springframework.security.core.userdetails.UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("--**-- 密码验证：" + username);
        return userAccountCmdService.getUserInfo(username);
    }
}
