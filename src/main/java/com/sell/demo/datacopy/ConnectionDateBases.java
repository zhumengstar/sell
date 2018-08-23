package com.sell.demo.datacopy;

import java.sql.*;

public class ConnectionDateBases {
    /**
     * 资源服务器连接
     **/
    private static String targetUrl = UtilConfig.targetUrl;
    /**
     * 本地服务器连接
     **/
    private static String nativeUrl = UtilConfig.nativeUrl;
    /**
     * 用户名
     */
    private static String userName = "root";
    /**
     * 密码
     */
    private static String password = "123456";

    /**
     * 连接
     */
    private static String driver = "com.mysql.jdbc.Driver";


    public Connection getNativeConnection() {
        Connection conn = null;
        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(nativeUrl, userName, password);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }

    public Connection getSourceConnection() {
        Connection conn = null;
        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(targetUrl, userName, password);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }

    /**
     * 释放连接
     *
     * @param conn
     */
    private static void freeConnection(Connection conn) {
        try {
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 释放statement
     *
     * @param statement
     */
    private static void freeStatement(Statement statement) {
        try {
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 释放statement
     *
     * @param statement
     */
    private static void freePreStatement(PreparedStatement statement) {
        try {
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    /**
     * 释放resultset
     *
     * @param rs
     */
    private static void freeResultSet(ResultSet rs) {
        try {
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 释放资源
     *
     * @param conn
     * @param statement
     * @param rs
     */
    public static void free(Connection conn, PreparedStatement preparedStatement, Statement statement, ResultSet rs) {
        if (rs != null) {
            freeResultSet(rs);
        }
        if (preparedStatement != null) {
            freePreStatement(preparedStatement);
        }
        if (statement != null) {
            freeStatement(statement);
        }
        if (conn != null) {
            freeConnection(conn);
        }
    }
}
