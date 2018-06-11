package com.xxw.architecture1.cartmgr.dao;

import org.springframework.stereotype.Repository;
import com.xxw.architecture1.common.dao.BaseDAO;

import com.xxw.architecture1.cartmgr.vo.CartModel;
import com.xxw.architecture1.cartmgr.vo.CartQueryModel;

@Repository
public interface CartDAO extends BaseDAO<CartModel,CartQueryModel>{
	
}
