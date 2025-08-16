package com.cn.mybatis.utils;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
@Order(Integer.MAX_VALUE)
public class MybatisUtil {
    private static SqlSessionFactory sqlSessionFactory;

    public MybatisUtil(SqlSessionFactory sqlSessionFactory) {
        MybatisUtil.sqlSessionFactory = sqlSessionFactory;
    }

     /**
     * mybatis-plus 批量入库
     *
     * @param list 需要入库的集合
     * @param mapperClass 对应的Mapper的class
     * @param <M> Mapper
     * @param <T> Entity
     */
    public static <M extends BaseMapper<T>, T> void insertBatch(List<T> list, Class<M> mapperClass) {
        if (CollUtil.isEmpty(list)) {
            return;
        }
        int size = list.size();
        try (SqlSession sqlSession = sqlSessionFactory.openSession(ExecutorType.BATCH, false)) {
            M mapper = sqlSession.getMapper(mapperClass);
            try {
                for (int i = 0; i < size; i++) {
                    mapper.insert(list.get(i));
                    if (i > 0 && ((i % 1000 == 0) || i == size - 1)) {
                        sqlSession.flushStatements();
                    }
                }
                sqlSession.commit();
            } catch (Exception e) {
                sqlSession.rollback();
                throw new RuntimeException("批量入库失败,总数:" + size, e);
            }
        }
    }
}
