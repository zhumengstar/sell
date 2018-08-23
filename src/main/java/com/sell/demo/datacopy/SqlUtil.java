package com.sell.demo.datacopy;

import java.sql.Connection;
import java.util.List;

public class SqlUtil {
    //本地资源库
    Connection natiCon = new ConnectionDateBases().getNativeConnection();
    //对方资源库
    Connection sourceCon = new ConnectionDateBases().getSourceConnection();

    /**
     * 生成增加接入时间的列名
     **/
    public String alertSql(String tableName) {
        StringBuffer sb = new StringBuffer();
        sb.append("ALTER table " + tableName + " add     gp_updatetime timestamp null");
        return sb.toString();
    }

    /**
     * 全量同步
     * 本地结构跟源结构一致,查询本地结构加字段
     * 生成预处理数据库插入语句
     **/

    public String preSql(String tableName) {
        String natiTable = UtilConfig.pre_tableName + tableName;
        List list = new SyDateDao().Find_table_field(sourceCon, tableName);
        StringBuffer sb = new StringBuffer();
        sb.append("  REPLACE INTO " + natiTable + "(");
        for (int i = 0; i < list.size(); i++) {
            sb.append(list.get(i) + ",");
        }
        sb.append("gp_updatetime )values(");
        for (int i = 0; i < list.size(); i++) {
            sb.append("?,");
        }
        sb.append("CURRENT_TIMESTAMP )");


        return sb.toString();
    }


    /**
     * 增量同步
     * 本地结构跟源结构一致,查询本地结构加字段
     * 生成预处理数据库插入语句
     **/

    public String addPreSql(String tableName) {
        String natiTable = UtilConfig.pre_tableName + tableName;
        List list = new SyDateDao().Find_table_field(sourceCon, tableName);
        StringBuffer sb = new StringBuffer();
        sb.append("INSERT INTO " + natiTable + "(");
        for (int i = 0; i < list.size(); i++) {
            sb.append(list.get(i) + ",");
        }
        sb.append("gp_updatetime )values(");
        for (int i = 0; i < list.size(); i++) {
            sb.append("?,");
        }
        sb.append("CURRENT_TIMESTAMP )");
        return sb.toString();
    }

    /**
     * 拿到另一个库中额中的表字段 by ysh
     **/

    public String allSql(Connection con, String databasename, String tablename) {

        List<Tablestructure> list = new SyDateDao().listsql(con, databasename, tablename);
        String alllsitsql = "";
        for (Tablestructure tablestructure : list) {
            alllsitsql += "`" + tablestructure.getField() + "`" + "  " + tablestructure.getType() + " ";
            if (tablestructure.getNull().equals("NO")) {
                alllsitsql += "NOT NULL" + " ";
            }
            if (tablestructure.getExtra().equals("auto_increment") && tablestructure.getKey().equals("PRI")) {
                alllsitsql += "AUTO_INCREMENT" + ",";
            }
            if (tablestructure.getDefault() == null && tablestructure.getNull().trim().equals("YES")) {
                alllsitsql += "DEFAULT NULL " + " ";
            }
            if (tablestructure.getKey().trim().contains("PRI")) {
//                alllsitsql += " PRIMARY KEY (`" + tablestructure.getField() + "`)";
                alllsitsql += " PRIMARY KEY";
            }
            alllsitsql += ",";

        }
        String allString = alllsitsql.substring(0, alllsitsql.length() - 1);
        String sql2 = "CREATE TABLE "+ UtilConfig.pre_tableName+tablename + " (" + allString + " ) DEFAULT CHARSET=utf8";
        return sql2;
    }

    /**
     * 创建数据库 前缀一gp_clone命名
     **/
    public String createDataBase(String dataBaseName) {
        return "create   database  " + UtilConfig.pre_dataBase + "_" + dataBaseName;
    }

    /**
     * 验证表是否存在 by ysh
     **/
    public String Tableexist( String tablename) {
        String sql1 = " DROP TABLE IF EXISTS " + tablename + ";";
        return sql1;
    }

    public String useSQL(String nativeName) {
        return "use " + nativeName + " ; ";
    }
}
