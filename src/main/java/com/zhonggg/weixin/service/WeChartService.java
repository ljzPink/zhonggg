package com.zhonggg.weixin.service;

import com.zhonggg.weixin.dto.WeChatMessageDTO;
import com.zhonggg.weixin.interf.Hander;
import com.zhonggg.weixin.interf.Hkey;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @description:
 * @author: lijinzhong
 * @create: 2020-04-18 09:45
 */
@Service
public class WeChartService implements ApplicationContextAware {
    /*
     * 自定义token, 用作生成签名,从而验证安全性
     * */
    @Value("${weChart.token}")
    private String token ;

    @Value("${weChart.appid}")
    private   String appid;

    @Value("${weChart.secret}")
    private   String secret;

    /*@Autowired
    private WeChartApi weChartApi;

    @Autowired
    private CacheAsyncService cacheAsyncService;*/
    public String auth(String signature, String timestamp, String nonce, String echostr) {

        String sortStr = sort(token,timestamp,nonce);
        String mySignature = shal(sortStr);

        if(!"".equals(signature) && !"".equals(mySignature) && signature.equals(mySignature)){
            return echostr;
        }else {
            return null;
        }

    }
    public String sort(String token, String timestamp, String nonce) {
        String[] strArray = {token, timestamp, nonce};
        Arrays.sort(strArray);
        StringBuilder sb = new StringBuilder();
        for (String str : strArray) {
            sb.append(str);
        }
        return sb.toString();
    }
    public String shal(String str){
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-1");
            digest.update(str.getBytes());
            byte messageDigest[] = digest.digest();

            StringBuffer hexString = new StringBuffer();
            // 字节数组转换为 十六进制 数
            for (int i = 0; i < messageDigest.length; i++) {
                String shaHex = Integer.toHexString(messageDigest[i] & 0xFF);
                if (shaHex.length() < 2) {
                    hexString.append(0);
                }
                hexString.append(shaHex);
            }
            return hexString.toString();

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }
    private HashMap<String, Hander> handers=new HashMap<>();


    public Object response(WeChatMessageDTO msg) {
        String msgType = msg.getMsgType();
        String Event = msg.getEvent();
        String EventKey = msg.getEventKey();
        Hander hander = handers.get(msgType + "." + Event + "." + EventKey);
        if (hander!=null){
            return  hander.Hander(msg);
        }
        return null;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        Map<String, Hander> beansOfType = applicationContext.getBeansOfType(Hander.class);
        for ( Hander hander:  beansOfType.values()) {
            Hkey hkey = hander.getClass().getAnnotation(Hkey.class);
            handers.put(hkey.name(),hander);
        }
    }
}
