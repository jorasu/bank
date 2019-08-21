package com.kgc.test;

import com.kgc.dao.AccountMapper;
import com.kgc.dao.TransactionrecordMapper;
import com.kgc.pojo.Transactionrecord;
import com.kgc.service.AccountService;
import com.kgc.service.TransactionrecordService;
import com.kgc.utils.Page;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by jora on 2019/5/18.
 */
public class Test {
    public static void main(String[] args) {
        ApplicationContext ac=new ClassPathXmlApplicationContext("applicationContext.xml");
        AccountService  accountService=(AccountService)ac.getBean("accountServiceImpl");
         System.out.println(accountService.queryBalanceByCard("6546123165165465"));
        System.out.println(accountService.isLogin("6546123165165465","123456"));
        AccountMapper accountMapper=(AccountMapper)ac.getBean("accountMapper");
        System.out.println(accountMapper.queryAccByCard("6546123165165465"));
      /*  TransactionrecordMapper transactionrecordMapper=(TransactionrecordMapper)ac.getBean("transactionrecordMapper");
        Page page=new Page(1,5,10);
        System.out.println(page);

        Map<String,Object> map=new HashMap<String,Object>();
        map.put("startRow",page.getStartRow());
        map.put("pageSize",page.getPageSize());
        map.put("start","2019-06-10");
        map.put("end","2019-06-10");
        List<Transactionrecord> mapList= transactionrecordMapper.queryByPageInfo(map);
        System.out.println(mapList);*/
    //  System.out.println(mapList.get(0).getId());
     //  mapList.forEach(c-> System.out.println(c));

    }
}
