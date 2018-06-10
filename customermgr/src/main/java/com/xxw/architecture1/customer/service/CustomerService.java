package com.xxw.architecture1.customer.service;

import com.xxw.architecture1.common.service.BaseService;
import com.xxw.architecture1.customer.dao.CustomerDAO;
import com.xxw.architecture1.customer.vo.CustomerModel;
import com.xxw.architecture1.customer.vo.CustomerQueryModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CustomerService extends BaseService<CustomerModel, CustomerQueryModel> implements ICustomerService {

    private CustomerDAO dao;
    @Autowired
    public void setDao(CustomerDAO dao){
        this.dao = dao;
        super.setDAO(dao);
    }

}
