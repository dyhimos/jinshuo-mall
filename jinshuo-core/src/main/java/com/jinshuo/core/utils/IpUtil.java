package com.jinshuo.core.utils;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * Created by Administrator on 2020/4/2.
 */
@Slf4j
public class IpUtil {
    public static String getIpAddr(HttpServletRequest request) {
        String ipAddress = null;
        try {
            log.info("  --  IpUtil request:", request);
            log.info("  --  IpUtil JSONObject :" + JSONObject.toJSONString(request));
            ipAddress = request.getHeader("x-forwarded-for");
            if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
                ipAddress = request.getHeader("Proxy-Client-IP");
                log.info("  --  x-forwarded-for ipAddress == null ipAddress1:" + ipAddress);
            }
            if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
                ipAddress = request.getHeader("WL-Proxy-Client-IP");
                log.info("  --  WL-Proxy-Client-IP ipAddress == null ipAddress2:" + ipAddress);
            }
            if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
                ipAddress = request.getRemoteAddr();
                if (ipAddress.equals("127.0.0.1")) {
                    // 根据网卡取本机配置的IP
                    InetAddress inet = null;
                    try {
                        inet = InetAddress.getLocalHost();
                    } catch (UnknownHostException e) {
                        e.printStackTrace();
                    }
                    ipAddress = inet.getHostAddress();
                }
            }
            // 对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割
            if (ipAddress != null && ipAddress.length() > 15) { // "***.***.***.***".length()
                // = 15
                if (ipAddress.indexOf(",") > 0) {
                    ipAddress = ipAddress.substring(0, ipAddress.indexOf(","));
                }
            }
        } catch (Exception e) {
            log.error("  -- IpUtil报错，错误信息", e);
            ipAddress = "127.0.0.1";
        }
        // ipAddress = this.getRequest().getRemoteAddr();
        return ipAddress;
    }
}
