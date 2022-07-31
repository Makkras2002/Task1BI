package dao;

import entity.Data;
import exception.DaoException;

public interface DataDao {
    void create(Data data) throws DaoException;
    String selectSumAndMedian() throws DaoException;
}
