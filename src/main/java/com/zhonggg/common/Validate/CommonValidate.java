package com.zhonggg.common.Validate;

import com.zhonggg.common.dto.PageDTO;
import com.zhonggg.commonUtils.exceptions.GlobalException;
import org.apache.commons.lang.StringUtils;

/**
 * @description:
 * @author: lijinzhong
 * @create: 2020-09-17 19:44
 */
public class CommonValidate {
    public static void pageDtoValidate(PageDTO pageDTO) {
        StringBuilder builder = new StringBuilder();
        Integer pageNo = pageDTO.getPageNo();
        Integer pageSize = pageDTO.getPageSize();
        if (pageNo == null || pageSize == null) {
            builder.append("分页参数不对");
        }
        if (StringUtils.isNotBlank(builder.toString())) {
            throw new GlobalException(builder.toString());
        }
        if(pageSize != -1){
            pageDTO.setStartIndex((pageNo -1)* pageSize);
        }
    }
}
