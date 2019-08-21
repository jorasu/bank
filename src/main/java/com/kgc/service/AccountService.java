package com.kgc.service;

import com.kgc.pojo.Account;
import com.kgc.vo.TransferVo;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * Created by jora on 2019/5/18.
 */
public interface AccountService {
    //1.登录
    String isLogin(String cardNo,String pwd);

    //2.查询余额
    BigDecimal queryBalanceByCard(String cardNo);

     //3.转账  转出账户 转账金额
    String transferAccount(TransferVo transferVo);

    //4.修改账户密码
    String updtePwd(Account updateAcc);


}
