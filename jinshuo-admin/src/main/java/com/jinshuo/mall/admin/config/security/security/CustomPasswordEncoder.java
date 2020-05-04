package com.jinshuo.mall.admin.config.security.security;

import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Created by Administrator on 2019/5/1 0001.
 */
public class CustomPasswordEncoder implements PasswordEncoder {

    @Override
    public String encode(CharSequence charSequence) {
        return charSequence.toString();
    }

    @Override
    public boolean matches(CharSequence charSequence, String s) {
        return s.equals(charSequence.toString());
    }
}
