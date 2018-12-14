package com.spring.test;


import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.spring.model.Account;
import com.spring.model.Department;
import com.spring.model.Role;
import com.spring.repository.AccountRepository;
import com.spring.repository.DepartmentRepository;
import com.spring.repository.RoleRepository;

import java.util.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {JpaConfiguration.class})
public class MysqlTest {
    private static Logger logger = LoggerFactory.getLogger(MysqlTest.class);

    @Autowired
    AccountRepository accountRepository;
    @Autowired
    DepartmentRepository departmentRepository;
    @Autowired
    RoleRepository roleRepository;

    @Before
    @Transactional
    public void initData(){
    	accountRepository.deleteAll();
        roleRepository.deleteAll();
        departmentRepository.deleteAll();

        Department department = new Department();
        department.setName("开发部");
        departmentRepository.save(department);
        Assert.notNull(department.getId());

        Role role = new Role();
        role.setName("admin");
        roleRepository.save(role);
        Assert.notNull(role.getId());

        Account account = new Account();
        account.setUserName("user");
        account.setCreateDate(new Date());
        account.setDeparment(department);

        List<Role> roles = roleRepository.findAll();
        Assert.notNull(roles);
        account.setRoles(roles);

        accountRepository.save(account);
        Assert.notNull(account.getId());
    }

    /* TODO 冯剑飞事务管理 */
    @Test
    public void findPage(){
        Pageable pageable = new PageRequest(0, 10, new Sort(Sort.Direction.ASC, "id"));
        Page<Account> page = accountRepository.findAll(pageable);
        Assert.notNull(page);
        for(Account account : page.getContent()) {
            logger.info("====user==== user name:{}, department name:{}, role name:{}",
            		account.getUserName(), account.getDeparment().getName(), account.getRoles().get(0).getName());
        }
    }

    //@Test
    /*
    public void test(){
        Account user1 = accountRepository.findByNameLike("u%");
        Assert.notNull(user1);

        Account user2 = accountRepository.readByName("user");
        Assert.notNull(user2);

        List<Account> users = accountRepository.getByCreatedateLessThan(new Date());
        Assert.notNull(users);
    }
    */
}
