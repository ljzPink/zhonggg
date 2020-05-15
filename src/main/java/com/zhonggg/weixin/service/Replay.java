package com.zhonggg.weixin.service;

import com.zhonggg.weixin.dto.WeChatMessageDTO;
import com.zhonggg.weixin.interf.Hander;
import com.zhonggg.weixin.interf.Hkey;
import com.zhonggg.weixin.vo.NewItems;
import com.zhonggg.weixin.vo.WeChatMessageVo;
import lombok.Data;
import org.apache.commons.collections.CollectionUtils;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * @description:
 * @author: lijinzhong
 * @create: 2020-04-18 09:56
 */
@Hkey(name="text.null.null")
public class Replay  implements Hander {
    private String unfind= "对不起，没找到你搜索的数据。\n要不，联系一下客服？或者我给你笑一个？\uE057";
    @Override
    public Object Hander(WeChatMessageDTO msg) {
        WeChatMessageVo out = new WeChatMessageVo();
        //把原来的发送方设置为接收方
        out.setToUserName(msg.getFromUserName());
        //把原来的接收方设置为发送方
        out.setFromUserName(msg.getToUserName());
        //获取接收的消息类型
        String msgType = msg.getMsgType();
        String content = msg.getContent();
        //设置消息创建时间
        out.setCreateTime(System.currentTimeMillis());
        out.setMsgType("news");
        //用户自定义数据
        //List<Data> list = mapper.selectByNameLike(content);
        List<Data> list = new ArrayList<>();
        /*if(CollectionUtils.isEmpty(list)){

        }*/
        out.setMsgType("text");
        out.setContent(unfind);
        return out;
    }
}
