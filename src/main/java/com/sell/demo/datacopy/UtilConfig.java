package com.sell.demo.datacopy;

public class UtilConfig {

    /**
     * 定义数据库前缀
     **/
    final static String pre_dataBase = "gp_clone";
    /**
     * 定义数据表前缀
     **/
    final static String pre_tableName = "gp_";

    /**
     * 定义来源数据库链接
     **/
    final static String targetUrl = "jdbc:mysql://127.0.0.1:3306/sell";

    /**
     * 定义本地数据库链接
     **/
    final static String nativeUrl = "jdbc:mysql://127.0.0.1:3306/sell1";
}
