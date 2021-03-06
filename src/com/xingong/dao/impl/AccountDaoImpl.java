package com.xingong.dao.impl;

import com.xingong.dao.AccountDao;
import com.xingong.po.Account;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public class AccountDaoImpl implements AccountDao {
    JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int addAccount(Account account) {
        String sql="insert into account(username,balance) values(?,?)";
        Object[] obj=new Object[]{
                account.getUsername(),
                account.getBalance()
        };
        return this.jdbcTemplate.update(sql,obj);
    }

    @Override
    public int updateAccount(Account account) {
        String sql="update account set username=?,balance=? where id=?";
        Object[] obj=new Object[]{
                account.getUsername(),
                account.getBalance(),
                account.getId()
        };
        return this.jdbcTemplate.update(sql,obj);
    }

    @Override
    public int deleteAccount(int id) {
        String sql="delete from account where id=?";
        return this.jdbcTemplate.update(sql,id);
    }

    @Override
    public Account getAccountById(int id) {
        String sql="select * from account where id=?";
        RowMapper<Account> rowMapper=new BeanPropertyRowMapper<Account>(Account.class);

        return this.jdbcTemplate.queryForObject(sql,rowMapper,id);
    }

    @Override
    public List<Account> getAllAccount() {
        String sql="select * from account";
        RowMapper<Account> rowMapper=new BeanPropertyRowMapper<Account>(Account.class);
        return this.jdbcTemplate.query(sql,rowMapper);
    }

    /**
     * 转账
     * @param outUser    汇款人
     * @param inUser     收款人
     * @param balance    转账金额
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.DEFAULT,
                   readOnly = false)
    public void transfer(String outUser, String inUser, double balance) {
        //汇款
        this.jdbcTemplate.update("update account set balance=balance-? "
                   +"where username=?",balance,outUser);
//        int i=1/0;
        //收款
        this.jdbcTemplate.update("update account set balance=balance+? "
                +"where username=?",balance,inUser);
    }
}
