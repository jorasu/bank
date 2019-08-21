package com.kgc.controller;

import com.kgc.pojo.Transactionrecord;
import com.kgc.service.TransactionrecordService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by jora on 2019/5/18.
 */
@Controller
public class TransactionrecordController {
    @Resource
    private TransactionrecordService transactionrecordService;

    @ResponseBody
    @RequestMapping(value = "queryByPageInfo.do" ,produces ={"application/json;charset=utf-8"})
    public String queryByPageInfo(String start, String end, Integer pageNo){
        Map<String,Object> times=new HashMap<String,Object>();
        times.put("start",start);
        times.put("end",end);
        times.put("pageNo",pageNo);
      return transactionrecordService.queryByPageInfo(times);
    }
}
