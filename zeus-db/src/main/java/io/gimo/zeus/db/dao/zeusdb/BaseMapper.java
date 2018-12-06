package io.gimo.zeus.db.dao.zeusdb;

import java.io.Serializable;

public interface BaseMapper<Model, PK extends Serializable> {

    int save(Model record);

    int saveSelective(Model record);

    int removeByPrimaryKey(PK id);

    int updateByPrimaryKey(Model record);

    int updateByPrimaryKeySelective(Model record);

    int getByPrimaryKey(PK id);

}
