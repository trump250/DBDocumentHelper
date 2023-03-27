package com.zcr.DBDocument.databaseFactory;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import javax.sql.DataSource;

/**
 * @Projectname: DocumentsHelper
 * @Filename: OracleFactory
 * @Author: zcr
 * @Data:2023/3/18 9:43
 * @Description:
 */
public class OracleFactory implements DatabaseFactory {
    @Override
    public DataSource createDataSource() {
        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setDriverClassName("oracle.jdbc.driver.OracleDriver");
        hikariConfig.setJdbcUrl("jdbc:oracle:thin:@localhost:1521:oracle");
        hikariConfig.setUsername("root");
        hikariConfig.setPassword("root");
        //设置可以获取tables remarks信息
        hikariConfig.addDataSourceProperty("useInformationSchema", "true");
        hikariConfig.setMinimumIdle(2);
        hikariConfig.setMaximumPoolSize(5);
        return new HikariDataSource(hikariConfig);
    }
}
