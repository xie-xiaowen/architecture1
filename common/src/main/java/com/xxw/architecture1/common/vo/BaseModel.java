package com.xxw.architecture1.common.vo;

import com.xxw.pageutil.Page;

import java.io.Serializable;
import java.util.Objects;

public class BaseModel implements Serializable {

    private int uuid;  // uuid 唯一标识
    private Page page = new Page();

    public Page getPage(){
        return this.page;
    }

    public void setPage(Page page){
        this.page = page;
    }

    public int getUuid() {
        return uuid;
    }

    public void setUuid(int uuid) {
        this.uuid = uuid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BaseModel baseModel = (BaseModel) o;
        return uuid == baseModel.uuid;
    }

    @Override
    public int hashCode() {

        return Objects.hash(uuid);
    }
}
