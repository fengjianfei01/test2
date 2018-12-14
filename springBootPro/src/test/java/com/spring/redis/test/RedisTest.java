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
public class RedisTest {
    private static Logger logger = LoggerFactory.getLogger(RedisTest.class);

    @Autowired
    AccountRedis userRedis;

    @Before
    public void setup(){
        Department deparment = new Department();
        deparment.setName("开发部");

        Role role = new Role();
        role.setName("admin");

        Account user = new Account();
        user.setUserName("user");
        user.setCreateDate(new Date());
        user.setDeparment(deparment);

        List<Role> roles = new ArrayList<>();
        roles.add(role);

        user.setRoles(roles);

        //userRedis.delete(this.getClass().getName()+":userByname:"+user.getUserName());
        //userRedis.addAccount(this.getClass().getName()+":userByname:"+user.getUserName(), 10L, user);
        
        userRedis.delete("20181112test");
        userRedis.addAccount("20181112test", 10L, user);

    }

    @Test
    public void get(){
//        Account user = userRedis.get(this.getClass().getName() + ":userByname:user");
    	Account user = userRedis.get("20181112test");
        Assert.notNull(user);
        logger.info("======user====== name:{}, deparment:{}, role:{}",
                user.getUserName(), user.getDeparment().getName(), user.getRoles().get(0).getName());
    }
}
