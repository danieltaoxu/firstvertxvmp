package com.database;

import com.models.EntityWithID;

import java.util.List;

public class DishDAO implements DataAcessObject {

    @Override
    public void createData() {

    }

    @Override
    public void updateData(EntityWithID aData) {

    }

    @Override
    public void deleteData(EntityWithID aData) {

    }

    @Override
    public <Data extends EntityWithID> Data getData(long aDataId) {
        return null;
    }

    @Override
    public List<? extends EntityWithID> getAllData() {
        return null;
    }
}
