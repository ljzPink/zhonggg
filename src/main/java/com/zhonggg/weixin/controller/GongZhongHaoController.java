package com.zhonggg.weixin.controller;

import com.zhonggg.commonUtils.Result;
import com.zhonggg.commonUtils.ResultUtil;
import com.zhonggg.commonUtils.log.LoggerUtil;
import com.zhonggg.weixin.dto.WeChatMessageDTO;
import com.zhonggg.weixin.service.WeChartService;
import com.zhonggg.weixin.vo.WeChatMessageVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

/**
 * @description:
 * @author: lijinzhong
 * @create: 2020-04-08 16:31
 */
@RestController()
@RequestMapping("/gongzhonghao")
public class GongZhongHaoController {
    @Autowired(required = false)
    private WeChartService weChartService;

    private Unmarshaller unmarshaller;

    private Marshaller marshaller;

    @PostConstruct
    private void init() {
        JAXBContext context = null;
        try {
            context = JAXBContext.newInstance(WeChatMessageDTO.class);
            unmarshaller = context.createUnmarshaller();
        } catch (JAXBException e) {
            LoggerUtil.LOGGER.error(e.toString());
        }
        try {
            context = JAXBContext.newInstance(WeChatMessageVo.class);

            marshaller = context.createMarshaller();

            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            marshaller.setProperty(Marshaller.JAXB_ENCODING,"UTF-8");

        } catch (JAXBException e) {
            LoggerUtil.LOGGER.error(e.toString());
        }
    }


    @GetMapping(value = "auth")
    public String auth(  @RequestParam(name="signature") String signature,
                         @RequestParam(name="timestamp") String timestamp,
                         @RequestParam(name="nonce") String nonce,
                         @RequestParam(name="echostr") String echostr){
        return  weChartService.auth(signature,timestamp,nonce,echostr);

    }
    @PostMapping(value = "auth",produces = MediaType.APPLICATION_XML_VALUE)
    public void event(HttpServletRequest request,HttpServletResponse response  ){
        try {
            WeChatMessageDTO weChatMessageBo = (WeChatMessageDTO) unmarshaller.unmarshal(request.getInputStream());
            Object res = weChartService.response(weChatMessageBo);

            response.setCharacterEncoding("UTF-8");

            marshaller.marshal(res, response.getWriter());

        } catch (Exception e) {
            LoggerUtil.LOGGER.error(e.toString());
        }

    }

    @GetMapping("/getinto")
    public Result getinto(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Enumeration pNames = request.getParameterNames();
        while (pNames.hasMoreElements()) {
            String name = (String) pNames.nextElement();
            String value = request.getParameter(name);
            // out.print(name + "=" + value);

            String log = "name =" + name + "     value =" + value;
        }

        String signature = request.getParameter("signature");/// 微信加密签名
        String timestamp = request.getParameter("timestamp");/// 时间戳
        String nonce = request.getParameter("nonce"); /// 随机数
        String echostr = request.getParameter("echostr"); // 随机字符串
        PrintWriter out = response.getWriter();

        out.print(echostr);
        out.close();
        out = null;
        return ResultUtil.success();
    }
}
