package com.xxw.architecture1.storemgr.dao;

import org.springframework.stereotype.Repository;
import com.xxw.architecture1.common.dao.BaseDAO;

import com.xxw.architecture1.storemgr.vo.StoreModel;
import com.xxw.architecture1.storemgr.vo.StoreQueryModel;

@Repository
public interface StoreDAO extends BaseDAO<StoreModel,StoreQueryModel>{

    public StoreModel getOrderNumByGoodsUuid(int goodsUuid);

}
