package com.kgc.service.impl;

import com.kgc.commons.Commons;
import com.kgc.dao.AccountMapper;
import com.kgc.dao.TransactionrecordMapper;
import com.kgc.pojo.Account;
import com.kgc.pojo.Transactionrecord;
import com.kgc.service.AccountService;
import com.kgc.vo.TransferVo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by jora on 2019/5/18.
 * spring是默认是基于AOP进行事务处理的，
 * 但基于aop事务处理仅支持外部程序来触发事务
 * 如需要触发内部程序的事务处理，需在业务层、控制层加事务注解处理
 *
 * @Transactional
 *        propagation:事务的传播机制
 *        propagation= Propagation.REQUIRED  默认事务的传播机制
 */
@Service
@Transactional
public class AccountServiceImpl implements AccountService {
    @Resource
    private AccountMapper mapper;
    @Resource
    private TransactionrecordMapper tMapper;
    @Override
    public String isLogin(String cardNo, String pwd) {
        //定义变量接收登录信息
        String loginMes="";
       Map<String,Object> mapAcc=mapper.queryAccByCard(cardNo);
        //判断卡号是否为空
        if(mapAcc!=null){
            //判断密码是否正确，用户状态是否为1
            String password=(String)mapAcc.get("password");
            Integer status=(Integer)mapAcc.get("status");
          if(password.equals(pwd)&&status==1){
              loginMes="loginOk";
            }else if(!password.equals(pwd)){
              loginMes="密码错误！";
          }else if (status==0){
                loginMes="账户已被冻结！";
            }
        }else {
            loginMes="您输入的卡号不存在！" ;
        }
        return loginMes;
    }

    @Override
    public BigDecimal queryBalanceByCard(String cardNo) {
        return mapper.queryBalanceByCard(cardNo);
    }

    @Override
    public String transferAccount(TransferVo transferVo) {
        //定义一个参数接收转账信息
        String transferMes="";

        //从前台获得转账金额，转入账号，转出账号
       // String Outcardno =(String)transferMap.get("Outcardno");
        String Outcardno=transferVo.getOutcardno();
        BigDecimal OutBalance=mapper.queryBalanceByCard(Outcardno);
     //   String  Incardno =(String)transferMap.get("Incardno");
        String  Incardno=transferVo.getIncardno();
        BigDecimal InBalance=mapper.queryBalanceByCard(Incardno);
        Map<String,Object> InMapAcc=mapper.queryAccByCard(Incardno);
       // BigDecimal tranferAmount=(BigDecimal)transferMap.get("tranferAmount");
        BigDecimal tranferAmount=transferVo.getTranferAmount();
        if(InMapAcc!=null){
                Integer status=(Integer)InMapAcc.get("status");
                if(status==1&&OutBalance.compareTo(tranferAmount)>=0){
                    //转账
                    transferMes="transferOk";
                    //转账后账户剩余金额
                    BigDecimal balanceOut=OutBalance.subtract(tranferAmount);
                    BigDecimal balanceIn=InBalance.add(tranferAmount);

                    Map<String, Object> OutMap=new HashMap<String, Object>();
                    OutMap.put("balance",balanceOut);
                    OutMap.put("cardno",Outcardno);
                    int outRes= mapper.updateAccount(OutMap);
                    Map<String, Object> InMap=new HashMap<String, Object>();
                    InMap.put("balance",balanceIn);
                    InMap.put("cardno",Incardno);
                    int inRes=mapper.updateAccount(InMap);
                    if((outRes+inRes)>1){
                        //添加交易记录信息
                        //出账交易
                        Transactionrecord outT=new Transactionrecord();
                        outT.setCardno(Outcardno);
                        outT.setBalance(balanceOut);
                        outT.setTransactiontype(Commons.TRANSACTION_OUT);
                     //   outT.setRemark((String)transferMap.get("remark"));
                        outT.setRemark(transferVo.getRemark());
                        outT.setTransactionamount(tranferAmount);
                        //入账交易
                        Transactionrecord inT=new Transactionrecord();
                        inT.setCardno(Incardno);
                        inT.setBalance(balanceIn);
                        inT.setTransactiontype(Commons.TRANSACTION_In);
                   //     inT.setRemark((String)transferMap.get("remark"));
                        inT.setRemark(transferVo.getRemark());
                        inT.setTransactionamount(tranferAmount);

                        //出账/入账信息记录表
                        tMapper.addTrans(inT);
                        tMapper.addTrans(outT);
                    }

                }else if(status==0){
                    transferMes="转账失败！目标账户已被冻结！";
                }else if(OutBalance.compareTo(tranferAmount)<0){
                    transferMes="转账失败！转出账号余额不足！";
                }
        }else {
            transferMes="转账失败！目标账户不存在！";
        }

        return transferMes;
    }

    @Override
    public String updtePwd(Account updateAcc) {
        String updatePwdMes="";
        int updateRes=mapper.updtePwd(updateAcc);
        if(updateRes>0){
            updatePwdMes="updateOk";
        }else {
            updatePwdMes="密码修改失败！";
        }
        return updatePwdMes;
    }
}
