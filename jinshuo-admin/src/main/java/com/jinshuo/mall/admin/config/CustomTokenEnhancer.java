package com.jinshuo.mall.admin.config;

import com.jinshuo.core.model.UserAuthDto;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * token生成携带的信息
 *
 */

public class CustomTokenEnhancer implements TokenEnhancer {

	@Override
	public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
		final Map<String, Object> additionalInfo = new HashMap<>();
		UserAuthDto user = (UserAuthDto) authentication.getUserAuthentication().getPrincipal();
		//id
		additionalInfo.put("id", user.getId());
		//店铺id
		additionalInfo.put("shopId", user.getShopId());
		//昵称
		additionalInfo.put("nickname", user.getNickname());
		//商家id
		additionalInfo.put("merchantId",user.getMerchantId());
		additionalInfo.put("username", user.getUsername());
		additionalInfo.put("authorities", user.getAuthorities());
		((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(additionalInfo);
		return accessToken;
	}

}
