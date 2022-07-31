package dao.impl;

import dao.DataDao;
import entity.Data;
import exception.DaoException;

import java.sql.*;

public class CustomDataDao implements DataDao {
    private static final String SQL_CREATE_DATA= """
            INSERT INTO task1_data_table (date,latin,russian,int_value,double_value)
            VALUES (?,?,?,?,?)""";
    private static final String SQL_COUNT_INT_SUM_AND_MEDIAN__OF_DOUBLE= """
            SELECT SUM(int_value) AS int_sum,PERCENTILE_CONT(0.5) WITHIN GROUP(ORDER BY double_value) 
            AS double_median FROM task1_data_table""";
    public CustomDataDao() {
        try {
            DriverManager.registerDriver(new org.postgresql.Driver());
        } catch (SQLException exception) {
            System.out.println(exception.getMessage());
        }
    }
    @Override
    public void create(Data data) throws DaoException {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = DriverManager.getConnection(
                    "jdbc:postgresql://localhost:5432/Task1",
                    "postgres",
                    "19091970Ig");
            statement = connection.prepareStatement(SQL_CREATE_DATA);
            statement.setDate(1, Date.valueOf(data.getDate()));
            statement.setString(2,data.getLatin());
            statement.setString(3,data.getRussian());
            statement.setInt(4,data.getIntValue());
            statement.setDouble(5,data.getDoubleValue());
            statement.executeUpdate();
        } catch (SQLException exception) {
            throw new DaoException(exception.getMessage());
        } finally {
            try {
                if(!statement.isClosed()) {
                    statement.close();
                }
                if(!connection.isClosed()) {
                    connection.close();
                }

            } catch (SQLException exception) {
                throw new DaoException(exception.getMessage());
            }
        }
    }
    @Override
    public String selectSumAndMedian() throws DaoException {
        Connection connection = null;
        PreparedStatement statement = null;
        Integer sumOfInt = 0;
        Double medianOfDouble = 0.0;
        try {
            connection = DriverManager.getConnection(
                    "jdbc:postgresql://localhost:5432/Task1",
                    "postgres",
                    "19091970Ig");
            statement = connection.prepareStatement(SQL_COUNT_INT_SUM_AND_MEDIAN__OF_DOUBLE);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                sumOfInt = resultSet.getInt(1);
                medianOfDouble = resultSet.getDouble(2);
            }
            return "Cумма всех целых чисел -> " + sumOfInt+" || Медиана всех дробных чисел -> " + medianOfDouble;
        } catch (SQLException exception) {
            throw new DaoException(exception.getMessage());
        } finally {
            try {
                if(!statement.isClosed()) {
                    statement.close();
                }
                if(!connection.isClosed()) {
                    connection.close();
                }

            } catch (SQLException exception) {
                throw new DaoException(exception.getMessage());
            }
        }
    }

}
