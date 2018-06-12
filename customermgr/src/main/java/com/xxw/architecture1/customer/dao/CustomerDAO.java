package com.xxw.architecture1.customer.dao;

import com.xxw.architecture1.common.dao.BaseDAO;
import com.xxw.architecture1.customer.vo.CustomerModel;
import com.xxw.architecture1.customer.vo.CustomerQueryModel;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerDAO extends BaseDAO<CustomerModel, CustomerQueryModel> {

    public CustomerModel getCustomerById(String customerId);

}
