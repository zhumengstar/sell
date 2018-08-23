package com.sell.demo.datacopy;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class ExecuteSql {

    /**
     * 同步全部数据
     *
     * @param fromdate       :大于这个时间
     * @param todate         :小于这个时间
     * @param sourcedataName :资源数据库名称
     *
     **/
    public int executeSql(String fromdate, String todate, String sourcedataName) {
        //本地数据库名字
        String NativeDataBase= UtilConfig.pre_dataBase+"_"+sourcedataName;
        Connection natiCon = new ConnectionDateBases().getNativeConnection();
        Connection sourceCon = new ConnectionDateBases().getSourceConnection();
        SyDateDao dao = new SyDateDao();
        List nameList = dao.getTableDao(sourceCon, sourcedataName);
        //创建数据库
        new SyDateDao().createDataBase(natiCon,sourcedataName);
        for (int i = 0; i < nameList.size(); i++) {
            String tableName = nameList.get(i).toString();
            //创建表结构
            new SyDateDao().moveUse(sourceCon,natiCon,sourcedataName,tableName);
            //多加一个时间字段
            dao.alertTime(natiCon,  UtilConfig.pre_tableName+tableName);
            //同步数据
            dao.launchSyncData(sourceCon, natiCon, tableName, fromdate, todate,NativeDataBase);
        }
        if (sourceCon != null) {
            try {
                sourceCon.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (natiCon != null) {
            try {
                natiCon.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return 0;
    }





    public static void main(String[] args) {
        String fromdate = "2010-05-04 14:34:18";
        String todate = "2018-08-21 23:16:44";
        new ExecuteSql().executeSql(fromdate, todate, "sell");
    }
}
