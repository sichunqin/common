package com.example.hibernate2;

import com.example.hibernate2.dao.CustomerDao;
import com.example.hibernate2.dao.LinkManDao;
import com.example.hibernate2.domain.Customer;
import com.example.hibernate2.domain.LinkMan;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Hibernate2ApplicationTests {

    @Autowired
    private CustomerDao customerDao;

    @Autowired
    private LinkManDao linkManDao;
    @Test
    public void contextLoads() {
    }
    @Test
    public void test1(){
        System.out.println("Hello");

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

}
