package com.xxw.architecture1.common.service;

import com.xxw.architecture1.common.dao.BaseDAO;
import com.xxw.architecture1.common.vo.BaseModel;
import com.xxw.pageutil.Page;

import java.util.List;

public class BaseService<M, QM extends BaseModel> implements IBaseService<M, QM>{

    private BaseDAO<M, QM> dao;

    public void setDAO(BaseDAO<M, QM> dao){
        this.dao = dao;
    }


    @Override
    public void create(M m) {
        dao.create(m);
    }

    @Override
    public void update(M m) {
        dao.update(m);
    }

    @Override
    public void delete(int uuid) {
        dao.delete(uuid);
    }

    @Override
    public M getByUuid(int uuid) {
        return dao.getByUuid(uuid);
    }

    @Override
    public Page<M> getByConditionPage(QM qm) {
        List<M> list = dao.getByConditionPage(qm);
        qm.getPage().setResult(list);
        return qm.getPage();
    }
}
