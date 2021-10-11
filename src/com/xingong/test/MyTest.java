package com.xingong.test;

import com.xingong.dao.AccountDao;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MyTest {
    @Test
    public void transferTest(){
        ApplicationContext applicationContext=
                new ClassPathXmlApplicationContext("applicatinCOntext.xml");
        AccountDao accountDao= (AccountDao) applicationContext.getBean("accountDao");
        accountDao.transfer("赵六","王五",500.00);
        System.out.println("转账成功");
    }
}
