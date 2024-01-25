package com.cn.mybatis.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.ToIntBiFunction;

@Slf4j
@Component
@Order(Integer.MAX_VALUE)
public class MybatisUtil {
    @Autowired
    private SqlSessionFactory sqlSessionFactory;

    /**
     * mybatis 批量操作
     *
     * @param list 需要插入数据库的实体对象集合
     * @param mapperClass mapper
     * @param mapperRToIntBiFunction 指定单条插入方法,必须是单条插入
     */
    public <T, Mapper> void batchInsert(List<T> list, Class<Mapper> mapperClass, ToIntBiFunction<Mapper, T> mapperRToIntBiFunction) {
        //切换batch模式
        SqlSession sqlSession = sqlSessionFactory.openSession(ExecutorType.BATCH);
        try {
            Mapper mapper = sqlSession.getMapper(mapperClass);
            for (T item : list) {
                //调用方法
                mapperRToIntBiFunction.applyAsInt(mapper, item);
            }
            sqlSession.commit();//提交事务
        } catch (Exception e) {
            log.error("[batchInsert]批量插入报错", e);
        } finally {
            sqlSession.close();
        }
    }
}
