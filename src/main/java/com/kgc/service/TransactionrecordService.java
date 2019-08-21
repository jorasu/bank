package com.kgc.service;

import com.kgc.pojo.Transactionrecord;

import java.util.List;
import java.util.Map;

/**
 * Created by jora on 2019/5/18.
 */
public interface TransactionrecordService {
    //根据给定时间范围查询交易记录
    String queryByPageInfo(Map<String,Object> times);
}
