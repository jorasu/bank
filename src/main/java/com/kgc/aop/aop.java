package com.kgc.aop;



import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;

import java.util.Arrays;

/**
 * Created by jora on 2019/6/11.
 */
public class aop {
       private Logger logger=Logger.getLogger(aop.class);

       public void beforeLog(JoinPoint jp){
              logger.info("获取调用的当前对象："+jp.getTarget()+","+
                      "当前调用对象的方法名称："+jp.getSignature()+","+
                      "获取当前调用的方法参数信息："+ Arrays.toString(jp.getArgs()));
              System.out.println("************************调用前置通知*****************************");
       }
}
