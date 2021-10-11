package com.xingong.dao;

import com.xingong.po.Account;

import java.util.List;

public interface AccountDao {
    public int addAccount(Account account);
    public int updateAccount(Account account);
    public int deleteAccount(int id);
    public Account getAccountById(int id);
    public List<Account> getAllAccount();
    //转账
    public void transfer(String outUser, String inUser, double balance);
}
