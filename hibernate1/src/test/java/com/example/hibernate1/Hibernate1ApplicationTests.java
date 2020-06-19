package com.example.hibernate1;

import com.example.hibernate1.dao.CustomerDao;
import com.example.hibernate1.dao.LinkManDao;
import com.example.hibernate1.dao.TenantDao;
import com.example.hibernate1.domain.Customer;
import com.example.hibernate1.domain.LinkMan;

import com.example.hibernate1.domain.Tenant;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
@RunWith(SpringRunner.class)
@SpringBootTest
public class Hibernate1ApplicationTests {
    @Autowired
    private CustomerDao customerDao;

    @Autowired
    private LinkManDao linkManDao;

    @Autowired
    private TenantDao tenantDao;

    @Test
    public void contextLoads() {
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
            System.out.println(t.toUrl());
        }
        tenants = tenantDao.findByIsEnabled(true);
        for (Tenant t : tenants) {
            System.out.println(t.toUrl());
        }

    }

}
