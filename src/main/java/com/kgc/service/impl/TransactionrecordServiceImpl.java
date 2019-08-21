package com.kgc.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.kgc.dao.TransactionrecordMapper;
import com.kgc.pojo.Transactionrecord;
import com.kgc.service.TransactionrecordService;
import com.kgc.utils.Page;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by jora on 2019/5/18.
 */
@Service
public class TransactionrecordServiceImpl implements TransactionrecordService {
    @Resource
    private TransactionrecordMapper mapper;
    @Override
    public String queryByPageInfo(Map<String, Object> times) {
        //从前台拿到开始时间和结束时间,当前页

        String start=(String)times.get("start");
        String end=(String)times.get("end");
        Integer pageNo=(Integer) times.get("pageNo");
        Integer pageSize=2;
        int totalCount=mapper.queryCountByPageInfo(times);
        if(totalCount==0){
            JSONObject jsonObject=new JSONObject();
            jsonObject.put("transactionrecordList","failed");
            return jsonObject.toString();
        }else {
            Page page=new Page(pageNo,pageSize,totalCount);
            Map<String, Object> map=new HashMap<String, Object>();
            map.put("start",start);
            map.put("end",end);
            map.put("startRow",page.getStartRow());
            map.put("pageSize",pageSize);
            List<Transactionrecord> transactionrecordList=mapper.queryByPageInfo(map);
            JSONObject jsonObject=new JSONObject();
            jsonObject.put("transactionrecordList",transactionrecordList);
            jsonObject.put("page",page);
            return jsonObject.toString();
        }


    }
}
