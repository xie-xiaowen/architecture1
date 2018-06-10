package com.xxw.architecture1.customer;

import com.xxw.architecture1.customer.dao.CustomerDAO;
import com.xxw.architecture1.customer.service.ICustomerService;
import com.xxw.architecture1.customer.vo.CustomerModel;
import com.xxw.architecture1.customer.vo.CustomerQueryModel;
import com.xxw.pageutil.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Client {

    @Autowired
    private ICustomerService service = null;

    public static void main(String[] args) {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        Client client = (Client)ctx.getBean("client");

        CustomerModel cm = new CustomerModel();
        cm.setCustomerId("c1");
        cm.setPwd("c1");
        cm.setRegisterTime("2018-08-08");
        cm.setShowName("c1");
        cm.setTrueName("张三");

        // client.dao.create(cm);

        CustomerQueryModel cqm = new CustomerQueryModel();
        cqm.getPage().setNowPage(2);
        Page<CustomerModel> page = client.service.getByConditionPage(cqm);

        for (CustomerModel customerModel : page.getResult()) {
            System.out.println("cusomerModel = " + customerModel);
        }


    }


}
