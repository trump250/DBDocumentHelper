package com.zcr.DBDocument.databaseFactory;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import javax.sql.DataSource;

/**
 * @Projectname: DocumentsHelper
 * @Filename: MysqlFactory
 * @Author: zcr
 * @Data:2023/3/18 9:40
 * @Description:
 */
public class MysqlFactory implements DatabaseFactory {

    @Override
    public DataSource createDataSource() {
        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setDriverClassName("com.mysql.cj.jdbc.Driver");
        hikariConfig.setJdbcUrl("jdbc:mysql://47.103.39.207:3306/am-server-new?useSSL=false&useUnicode=true&characterEncoding=UTF-8&serverTimezone=GMT%2B8&zeroDateTimeBehavior=convertToNull");
        hikariConfig.setUsername("root");
        hikariConfig.setPassword("123.wmjsoft");
        //设置可以获取tables remarks信息
        hikariConfig.addDataSourceProperty("useInformationSchema", "true");
        hikariConfig.setMinimumIdle(2);
        hikariConfig.setMaximumPoolSize(5);
        return new HikariDataSource(hikariConfig);
    }
}
