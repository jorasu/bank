package com.kgc.controller;

import com.kgc.pojo.Account;
import com.kgc.service.AccountService;
import com.kgc.vo.TransferVo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by jora on 2019/5/18.
 */
@Controller
public class AccountController {
    @Resource
    private AccountService accountService;
    //登录
    @ResponseBody
    @RequestMapping(value = "login.do" ,produces ={"application/json;charset=utf-8"})
    public String isLogin(String cardNo, String pwd) {
      return accountService.isLogin(cardNo,pwd);
    }

    //查询余额
    @ResponseBody
    @RequestMapping(value = "queryBalance.do" ,produces ={"application/json;charset=utf-8"})
    public BigDecimal queryBalanceByCard(String cardNo) {
        return accountService.queryBalanceByCard(cardNo);
    }

    //转账
    @ResponseBody
    @RequestMapping(value = "transfer.do" ,produces ={"application/json;charset=utf-8"})
    public String transferAccount(TransferVo transferVo){
       /* Map<String, Object> transferMap=new HashMap<String, Object>();
        transferMap.put("Outcardno",transferVo.getOutcardno());
        transferMap.put("Incardno",transferVo.getIncardno());
        transferMap.put("tranferAmount",transferVo.getTranferAmount());
        transferMap.put("remark",transferVo.getRemark());*/
        return accountService.transferAccount(transferVo);
    }

    //修改密码
    @ResponseBody
    @RequestMapping(value = "updtePwd.do" ,produces ={"application/json;charset=utf-8"})
    public String updtePwd(String cardNo, String pwd) {
        Account updateAcc=new Account();
        updateAcc.setCardno(cardNo);
        updateAcc.setPassword(pwd);
        return accountService.updtePwd(updateAcc);
    }
}
