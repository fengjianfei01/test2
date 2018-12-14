package com.spring.redis.test;



import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

import com.spring.model.Account;
import com.spring.model.Department;
import com.spring.model.Role;
import com.spring.redis.repository.AccountRedis;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {RedisConfig.class, AccountRedis.class})
public class RedisListTest {
    private static Logger logger = LoggerFactory.getLogger(RedisListTest.class);

    @Autowired
    AccountRedis accountRedis;

    @Before
    public void setup(){
        Department deparment = new Department();
        deparment.setName("开发部");

        Role role = new Role();
        role.setName("admin");

        Account account = new Account();
        account.setUserName("user");
        account.setCreateDate(new Date());
        account.setDeparment(deparment);

        List<Role> roles = new ArrayList<>();
        roles.add(role);

        account.setRoles(roles);

        List<Account> listAccount = new ArrayList<>();
        listAccount.add(account);

        accountRedis.delete(this.getClass().getName()+":userList:"+account.getUserName());
        accountRedis.addAccount(this.getClass().getName()+":userList:"+account.getUserName(), 10L, listAccount);

    }

    @Test
    public void get(){
        List<Account> accountList = accountRedis.getList(this.getClass().getName() + ":userList:user");
        //Assert.notNull(accountList);
        for(Account account : accountList) {
            logger.info("======user====== name:{}, deparment:{}, role:{}",
            		account.getUserName(), account.getDeparment().getName(), account.getRoles().get(0).getName());
        }
    }
}
