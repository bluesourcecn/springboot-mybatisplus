package com.cn.mybatis.utils;

import org.apache.commons.lang.StringUtils;

/**
 * mybatisplus模糊查询输入特殊字符时进行转义处理
 * */
public class EscapeStrUtil {
    public static boolean needEscape(String before) {
        return before != null && !"null".equalsIgnoreCase(before) && (before.contains(".") || before.contains("\\") || before.contains("%") || before.contains("_"));
    }
    public static String escapeChar(String before){
        if(StringUtils.isNotBlank(before)){
            before = before.replaceAll("\\\\", "\\\\\\\\");
            before = before.replaceAll("_", "\\\\_");
            before = before.replaceAll("%", "\\\\%");
            before = before.replaceAll("\\.", "\\\\.");
        }
        return before ;
    }
}
