package com.kgc.dao;

import com.kgc.pojo.Account;
import com.kgc.pojo.Transactionrecord;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * Created by jora on 2019/5/18.
 */
@Repository
public interface AccountMapper {
    //1.根据卡号查询所有账户
   Map<String,Object> queryAccByCard(String cardNo);

    //2.根据卡号查询余额
    BigDecimal queryBalanceByCard(String cardNo);

    //3.更新账户信息
    int updateAccount(Map<String,Object> updateMap);

    //4.修改账户密码
    int updtePwd(Account updateAcc);

}
