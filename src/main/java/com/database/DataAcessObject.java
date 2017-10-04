package com.database;

import com.models.EntityWithID;

import java.util.List;

public interface DataAcessObject {
    public void createData();
    public void updateData(EntityWithID aData);
    public void deleteData(EntityWithID aData);
    public <Data extends EntityWithID> Data getData(long aDataId);
    public List<? extends EntityWithID> getAllData();
}

