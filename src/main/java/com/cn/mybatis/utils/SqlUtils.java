package com.cn.mybatis.utils;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.core.toolkit.support.SFunction;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;

import java.lang.invoke.SerializedLambda;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

@Slf4j
public class SqlUtils {
    public static String andEqualValue(String column, String value){
        return " and " + column + "='" + value + "' ";
    }

    public static String andLikeValue(String column, String value){
        return " and " + column + " like '%" + value + "%' ";
    }

    public static String andNptEqualNull(String column){
        return " and " + column + "<> '' ";
    }

    /**
     * 获取数据库字段名
     *
     * @param fn {@link SFunction}
     * @param <T> {@link T}
     * @param <R> {@link R}
     * @return 数据库字段名
     */
    public static <T, R> String toColumn(SFunction<T, R> fn) {
        try {
            Method method = fn.getClass().getDeclaredMethod("writeReplace");
            method.setAccessible(Boolean.TRUE);
            SerializedLambda lambda = (SerializedLambda) method.invoke(fn);
            // 获取方法名
            String methodName = lambda.getImplMethodName();
            String prefix = null;
            if(methodName.startsWith("get")){
                prefix = "get";
            }
            else if(methodName.startsWith("is")){
                prefix = "is";
            }
            if (prefix != null) {
                methodName = methodName.replace(prefix, "");
            }
            StringBuilder column  = new StringBuilder();
            if(!Character.isLowerCase(methodName.charAt(0))) {
                methodName = Character.toLowerCase(methodName.charAt(0)) + methodName.substring(1);
            }
            Class<?> clazz = Class.forName(lambda.getImplClass().replace("/", "."));
            Field field = clazz.getDeclaredField(methodName);
            if (field.isAnnotationPresent(TableField.class)) {
                TableField fieldAnnotation = field.getAnnotation(TableField.class);
                String value = fieldAnnotation.value();
                if (StringUtils.isNotBlank(value))
                    return value;
            }
            for (char c : methodName.toCharArray()) {
                if (Character.isLowerCase(c))
                    column.append(c);
                else
                    column.append("_").append(Character.toLowerCase(c));
            }
            return column.toString();
        } catch (Exception e) {
            log.error(e.getClass().getSimpleName() + ":", e);
        }
        return "";
    }

    /**
     * select 语句拼接字段
     *
     * @param distinct 是否需要去重
     * @param fns {@link SFunction}
     * @param <T> {@link T}
     * @param <R> {@link R}
     * @return 数据库字段名
     */
    public static <T, R> String toSelectColumn(boolean distinct, SFunction<T, R>... fns) {
        StringBuilder selectColumn = new StringBuilder();
        if (distinct) {
            selectColumn.append("DISTINCT").append(" ");
        }
        for (SFunction<T, R> fn : fns) {
            selectColumn.append(toColumn(fn)).append(",");
        }
        return selectColumn.toString().replaceAll(",$", "");
    }

}
