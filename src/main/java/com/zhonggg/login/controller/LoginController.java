package com.zhonggg.login.controller;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.Callable;

/**
 * @description:
 * @author: lijinzhong
 * @create: 2020-05-19 09:04
 */
@Controller
public class LoginController {

    @GetMapping("login/getQrCode")
    public @ResponseBody
    Map<String, Object> getQrCode() throws Exception {
        Map<String, Object> result = new HashMap<>();
        result.put("loginId", UUID.randomUUID());

        // app端登录地址
        String loginUrl = "http://localhost:8080/login/setUser/loginId/";
        result.put("loginUrl", loginUrl);
        result.put("image", createQrCode(loginUrl));
        return result;
    }

    private String createQrCode(String content) throws Exception {
        try (ByteArrayOutputStream out = new ByteArrayOutputStream()) {
            Hashtable<EncodeHintType, Object> hints = new Hashtable<EncodeHintType, Object>();
            hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
            hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
            hints.put(EncodeHintType.MARGIN, 1);
            BitMatrix bitMatrix = new MultiFormatWriter().encode(content, BarcodeFormat.QR_CODE, 400, 400, hints);
            int width = bitMatrix.getWidth();
            int height = bitMatrix.getHeight();
            BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            for (int x = 0; x < width; x++) {
                for (int y = 0; y < height; y++) {
                    image.setRGB(x, y, bitMatrix.get(x, y) ? 0xFF000000 : 0xFFFFFFFF);
                }
            }
            ImageIO.write(image, "JPG", out);
            return Base64.encodeBase64String(out.toByteArray());
        }
    }

    @GetMapping("login/setUser/{loginId}/{user}")
    public @ResponseBody Map<String, Object> setUser(@PathVariable String loginId, @PathVariable String user) {

        //ValueOperations<String, String> opsForValue = redisTemplate.opsForValue();
       /* String value = opsForValue.get(LOGIN_KEY + loginId);

        if (value != null) {
            // 保存认证信息
            opsForValue.set(LOGIN_KEY + loginId, user, 1, TimeUnit.MINUTES);

            // 发布登录广播消息
            redisTemplate.convertAndSend(Receiver.TOPIC_NAME, loginId);
        }*/

        Map<String, Object> result = new HashMap<>();
        result.put("loginId", loginId);
        result.put("user", user);
        return result;
    }

    /**
     * 等待二维码扫码结果的长连接
     *
     * @param loginId
     * @param session
     * @return
     */
    @GetMapping("login/getResponse/{loginId}")
    public @ResponseBody
    Callable<Map<String, Object>> getResponse(@PathVariable String loginId, HttpSession session) {

        // 非阻塞
        Callable<Map<String, Object>> callable = () -> {

            Map<String, Object> result = new HashMap<>();
            result.put("loginId", loginId);

            try {
                //ValueOperations<String, String> opsForValue = redisTemplate.opsForValue();
                // String user = opsForValue.get(LOGIN_KEY + loginId);
                String user = "";
                // 长时间不扫码，二维码失效。需重新获二维码
                if (user == null) {
                    result.put("success", false);
                    result.put("stats", "refresh");
                    return result;
                }

                // 已登录
                if (!user.equals(loginId)) {
                    // 登录成,认证信息写入session
                    //session.setAttribute(WebSecurityConfig.SESSION_KEY, user);
                    result.put("success", true);
                    result.put("stats", "ok");
                    return result;
                }

                // 等待二维码被扫
                try {
                    // 线程等待30秒
                    //receiver.getLoginLatch(loginId).await(30, TimeUnit.SECONDS);
                } catch (Exception e) {
                    e.printStackTrace();
                }

                result.put("success", false);
                result.put("stats", "waiting");
                return result;

            } finally {
                // 移除登录请求
                // receiver.removeLoginLatch(loginId);
            }
        };

        return callable;
    }
}
