package com.zkzy.portal.common.service.dao;

import com.zkzy.portal.common.utils.DateHelper;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

/**
 * Created by Administrator on 2017/6/26 0026.
 */
public class JdbcDao {
    private static String url;
    private static String driver;
    private static String username;
    private static String password;

    private Connection connection = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;

    static {
        Properties p = new Properties();
        InputStream in = JdbcDao.class.getClassLoader().getResourceAsStream("application.properties");
        try {
            p.load(in);
            if (p != null) {
                url = p.getProperty("spring.datasource.url");
                driver = p.getProperty("spring.datasource.driver-class-name");
                username = p.getProperty("spring.datasource.username");
                password = p.getProperty("spring.datasource.password");
            }
        } catch (IOException e) {

        } finally {
            try {
                in.close();
            } catch (IOException e) {
            }
        }

    }

    private Connection openConnection() throws Exception {
        Class.forName(driver);
        connection = DriverManager.getConnection(url, username, password);
        System.out.println("----------------connection success!---------------");
        return connection;
    }


    protected boolean executeSql(String sqlString) throws Exception {
        showSql(sqlString, null);
        preparedStatement = openConnection().prepareStatement(sqlString);
        return preparedStatement.execute();
    }

    protected ResultSet executeQuery(String sqlString, Object[] objects) throws Exception {
        showSql(sqlString,objects);
        preparedStatement = openConnection().prepareStatement(sqlString);
        if (objects != null) {
            for (int i = 0; i < objects.length; i++) {
                preparedStatement.setObject(i + 1, objects[i]);
            }
        }
        resultSet = preparedStatement.executeQuery();
        return resultSet;
    }

    protected int executeUpdate(String sqlString, Object[] objects) throws Exception {
        showSql(sqlString,objects);
        preparedStatement = openConnection().prepareStatement(sqlString);
        if (objects != null) {
            for (int i = 0; i < objects.length; i++) {
                if (objects[i] != null)
                    preparedStatement.setObject(i + 1, objects[i]);
            }
        }
        return preparedStatement.executeUpdate();
    }


    protected void closeAll() {
        try {
            if (null != resultSet) {
                resultSet.close();
            }
            if (null != preparedStatement) {
                preparedStatement.close();
            }
            if (null != connection) {
                connection.close();
            }
            System.out.println("----------------connection closed!---------------");
        } catch (Exception e) {
        }

    }

    private void showSql(String sqlString, Object[] objects) {
        System.out.println(DateHelper.getTime() + ">>>>>>>>>>>>>>>>>>sql:" + sqlString);
        if (objects != null){
            for (int i = 0; i < objects.length; i++) {
                System.out.println((i + 1) + ":" + objects[i]+" _ ");
            }
        }
    }


}
