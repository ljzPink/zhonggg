package com.zhonggg.weixin.validate;

import com.zhonggg.common.Validate.CommonValidate;
import com.zhonggg.weixin.dto.NewDTO;

/**
 * @description:
 * @author: lijinzhong
 * @create: 2020-09-17 19:23
 */
public class XiaoChengXuValidate {
    public static void getNewsValidate(NewDTO newDTO) {
        CommonValidate.pageDtoValidate(newDTO);
    }
}
