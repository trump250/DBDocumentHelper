package com.zcr.DBDocument.databaseFactory;

import com.zaxxer.hikari.HikariDataSource;

import javax.sql.DataSource;

/**
 * @Projectname: DocumentsHelper
 * @Filename: databaseFactory
 * @Author: zcr
 * @Data:2023/3/18 9:38
 * @Description:
 */
public interface DatabaseFactory {

    DataSource createDataSource();
}
