package com.zhonggg.commonUtils.log;

import java.lang.annotation.*;

/**
 * 操作日志注解
 *
 * @author wangxiaolong
 * @date 2019/7/4.
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface OperationLog {
    int operateType() default 0;//操作类型(1登录,2退出,3查询,4新增,5修改,6删除,7申请,8审批,9导入,10导出,99其它)

    String operateDesc() default "";//操作描述
}
