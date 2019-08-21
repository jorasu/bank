package com.kgc.dao;

import com.kgc.pojo.Transactionrecord;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by jora on 2019/5/18.
 */
@Repository
public interface TransactionrecordMapper {
    //添加交易记录
    int addTrans(Transactionrecord tc);

    //根据给定时间范围查询交易记录
    List<Transactionrecord> queryByPageInfo(Map<String,Object> times);

    /**
     * 根据指定时间范围查询所有条数
     */
    int queryCountByPageInfo(Map<String,Object> times);
}
