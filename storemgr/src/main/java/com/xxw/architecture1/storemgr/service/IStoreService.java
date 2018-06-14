package com.xxw.architecture1.storemgr.service;


import com.xxw.architecture1.common.service.IBaseService;

import com.xxw.architecture1.storemgr.vo.StoreModel;
import com.xxw.architecture1.storemgr.vo.StoreQueryModel;

public interface IStoreService extends IBaseService<StoreModel,StoreQueryModel>{

    public StoreModel getOrderNumByGoodsUuid(int goodsUuid);

}

