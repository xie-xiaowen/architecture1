package com.xxw.architecture1.ordermgr.dao;

import org.springframework.stereotype.Repository;
import com.xxw.architecture1.common.dao.BaseDAO;

import com.xxw.architecture1.ordermgr.vo.OrderDetailModel;
import com.xxw.architecture1.ordermgr.vo.OrderDetailQueryModel;

@Repository
public interface OrderDetailDAO extends BaseDAO<OrderDetailModel,OrderDetailQueryModel>{
	
}
