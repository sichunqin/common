package com.example.hibernate1;

import com.example.hibernate1.dao.CustomerDao;
import com.example.hibernate1.dao.LinkManDao;
import com.example.hibernate1.dao.RsyncUserDao;
import com.example.hibernate1.dao.TenantDao;
import com.example.hibernate1.domain.Customer;
import com.example.hibernate1.domain.LinkMan;

import com.example.hibernate1.domain.RsyncUser;
import com.example.hibernate1.domain.Tenant;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Hibernate1ApplicationTests {
    private class Inner {
        public String name;
        public String code;
    }
    @Autowired
    private CustomerDao customerDao;

    @Autowired
    private LinkManDao linkManDao;

    @Autowired
    private TenantDao tenantDao;

    @Autowired
    private RsyncUserDao rsyncUserDao;

    @Test
    public void contextLoads() {
    }

    @Test
    public void testListQuery1(){

        List<String> words = Arrays.asList("pen", "custom", "orphanage",
                "forest", "bubble", "butterfly");

        List<String> result = words.stream().filter(word -> word.length() > 5)
                .collect(Collectors.toList());

        String s = words.stream().filter(word -> word.length() > 5).findFirst().orElse(null);
        System.out.print(s);

        result.forEach(word -> System.out.println(word));

    }

    @Test
    public void testListQuery2(){

        List<Customer> customers = new ArrayList<>();
        Customer c1 = new Customer();
        c1.setCustName("name1");
        c1.setCustId((long)1);
        Customer c2 = new Customer();
        c2.setCustName("name2");
        c2.setCustId((long)2);
        customers.add(c1);
        customers.add(c1);


        Customer c3 = customers.stream().filter(c -> c.getCustId() == 1).findFirst().orElse(null);
        System.out.print(c3);
    }

    @Test
    @Transactional //配置事务
    @Rollback(false) //不自动回滚
    public void testAdd() {
        //创建一个客户，创建一个联系人
        Customer customer = new Customer();
        customer.setCustName("百度");

        LinkMan linkMan = new LinkMan();
        linkMan.setLkmName("小李");

        /**
         * 配置了客户到联系人的关系
         *      从客户的角度上：发送两条insert语句，发送一条更新语句更新数据库（更新外键）
         * 由于我们配置了客户到联系人的关系：客户可以对外键进行维护
         */
        customer.getLinkMans().add(linkMan);


        customerDao.save(customer);
        linkManDao.save(linkMan);
    }
    //could not initialize proxy - no Session
    //测试对象导航查询（查询一个对象的时候，通过此对象查询所有的关联对象）
    @Test
    @Transactional // 解决在java代码中的no session问题
    public void  testQuery1() {
        //查询id为1的客户
        Customer customer = customerDao.getOne((long)2);
        //对象导航查询，此客户下的所有联系人
        Set<LinkMan> linkMans = customer.getLinkMans();

        for (LinkMan linkMan : linkMans) {
            System.out.println(linkMan);
        }
    }

    @Test
    @Transactional // 解决在java代码中的no session问题
    public void  testQueryTenant() {
        //查询id为1的客户
        List<Tenant> tenants = tenantDao.findAll();

        for (Tenant t : tenants) {
            System.out.print(t);
            System.out.println(t.getUrl());
        }
        tenants = tenantDao.findByIsEnabled(true);
        for (Tenant t : tenants) {
            System.out.println(t.getUrl());
        }

    }

    @Test
    @Transactional // 解决在java代码中的no session问题
    public void  testQueryRsyncUser() {
        //查询id为1的客户
        List<RsyncUser> rsyncUsers = rsyncUserDao.findAll();
        RsyncUser one = rsyncUsers.get(0);
        System.out.print(one);
    }

}
