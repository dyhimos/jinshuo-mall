package com.jinshuo.core.utils;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author dongyh
 * @Title: SendMsgUtils
 * @ProjectName ym-platform
 * @Description: TODO
 * @date 2019/7/24 16:40
 */
@Slf4j
public  class SendMsgUtils {

    /**
     * 发送短信
     * @param message
     * @throws IOException
     */
    public static void sendMsg(String message) throws IOException {
        JSONObject jsonObject = JSON.parseObject(message);
        String username = jsonObject.getString("username");
        String password = jsonObject.getString("password");
        String signName = jsonObject.getString("signName");
        String mobile = jsonObject.getString("mobile");
        String content = jsonObject.getString("content");
        sendMsg(username,password,signName,mobile,content);
    }
    /**
     * 短信发送接口
     * @param mobile username
     * @param signName	password
     * @param mobile 手机号码
     * @param mobile 手机号码
     * @param msg	信息
     * @return 信息发送状态
     * @throws HttpException
     * @throws IOException
     */
    public static  int sendMsg(String username,String password,String signName,String mobile,String msg) throws  IOException {
        log.info("用户名："+username);
        log.info("密码："+password);
        log.info("签名:"+signName);
        //短信接口地址
        String url = "http://www.qybor.com:8500/shortMessage";
        HttpClient client = new HttpClient();

        PostMethod post = new PostMethod(url);

        post.addRequestHeader("Content-Type","application/x-www-form-urlencoded;charset=utf-8");
        //在头文件中设置转码
        NameValuePair[] data ={
                new NameValuePair("username",username),
                new NameValuePair("passwd",password),
                new NameValuePair("phone",mobile),
                new NameValuePair("msg",signName+msg),
                new NameValuePair("needstatus","true"),
                new NameValuePair("port",""),
                new NameValuePair("sendtime","")};
        post.setRequestBody(data);
        client.executeMethod(post);
        Header[] headers = post.getResponseHeaders();
        int statusCode = post.getStatusCode();
        //String result = new String(post.getResponseBodyAsString().getBytes());
        BufferedReader reader = new BufferedReader(new InputStreamReader(post.getResponseBodyAsStream()));
        StringBuffer stringBuffer = new StringBuffer();
        String str = "";
        while((str = reader.readLine())!=null){
            stringBuffer.append(str);
        }
        String 	result=stringBuffer.toString();


        //打印返回消息状态
        System.out.println(result);
        post.releaseConnection();
        //回调短信接口
        return statusCode;
    }



    /*public static  int sendTest(String mobile,String msg) throws  IOException {
        //短信接口地址
        String url = "http://www.qybor.com:8500/shortMessage";
        HttpClient client = new HttpClient();

        PostMethod post = new PostMethod(url);

        post.addRequestHeader("Content-Type","application/x-www-form-urlencoded;charset=utf-8");
        //在头文件中设置转码
        NameValuePair[] data ={
                new NameValuePair("username","ymzby3688"),
                new NameValuePair("passwd","ymtour@369"),
                new NameValuePair("phone",mobile),
                new NameValuePair("msg","【亿麦周边游】"+msg),
                new NameValuePair("needstatus","true"),
                new NameValuePair("port",""),
                new NameValuePair("sendtime","")};
        post.setRequestBody(data);
        client.executeMethod(post);
        Header[] headers = post.getResponseHeaders();
        int statusCode = post.getStatusCode();
        String result = new String(post.getResponseBodyAsString().getBytes());
        //打印返回消息状态
        System.out.println(result);
        post.releaseConnection();
        return statusCode;
    }

    public static void main(String[] args) {
        try {
            sendTest("18300198837","您的验证码是733298，请勿泄露于他人，以免账号被盗！");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }*/
}
