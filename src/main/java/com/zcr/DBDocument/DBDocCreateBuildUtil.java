package com.zcr.DBDocument;

import cn.smallbun.screw.core.Configuration;
import cn.smallbun.screw.core.engine.EngineConfig;
import cn.smallbun.screw.core.engine.EngineFileType;
import cn.smallbun.screw.core.engine.EngineTemplateType;
import cn.smallbun.screw.core.execute.DocumentationExecute;
import cn.smallbun.screw.core.process.ProcessConfig;
import com.zcr.DBDocument.databaseFactory.MysqlFactory;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

/**
 * @Projectname: DocumentsHelper
 * @Filename: DBDocCreateBuildUtil
 * @Author: zcr
 * @Data:2023/3/18 9:03
 * @Description:
 */
public class DBDocCreateBuildUtil {


    public static void main(String[] args) {
        // 1.获取数据源
        DataSource dataSource = getDataSource();
        // 2.获取数据库文档生成配置（文件路径、文件类型）
        EngineConfig engineConfig = getEngineConfig();
        // 3.获取数据库表的处理配置，可忽略
        ProcessConfig processConfig = getProcessConfigIgnore();
        // 4.Screw 完整配置
        Configuration config = getScrewConfig(dataSource, engineConfig, processConfig);
        // 5.执行生成数据库文档
        new DocumentationExecute(config).execute();
    }

    /**
     * 获取数据库源
     * 对数据库以及数据库连接池进行相关配置。务必将数据库相关的配置修改成你自己的。
     */
    private static DataSource getDataSource() {
        //数据源
        MysqlFactory mysqlFactory = new MysqlFactory();
        return mysqlFactory.createDataSource();
    }

    /**
     * 获取文件生成配置
     * 这一步会指定数据库文档生成的位置、文件类型以及文件名称。
     * 如果不配置生成文件路径的话，默认也会存放在项目的 doc 目录下。
     * <p>
     * 另外，我们这里指定生成的文件格式为 HTML。除了 HTML 之外，screw 还支持 Word 、Markdown 这两种文件格式。
     * <p>
     * 不太建议生成 Word 格式,比较推荐 Markdown 格式。
     */
    private static EngineConfig getEngineConfig() {
        //生成配置
        return EngineConfig.builder()
                //生成文件路径
                .fileOutputDir("C:\\Users\\15336\\Desktop")
                //打开目录
                .openOutputDir(true)
                //文件类型
                .fileType(EngineFileType.WORD)
                //生成模板实现
                .produceType(EngineTemplateType.freemarker)
                //自定义文件名称
                .fileName("am-server-new结构文档").build();
    }

    /**
     * 单独设置需要在文档里的表
     * @return
     */
    private static ProcessConfig getProcessConfigAssign() {
        //可以设置多个
        List<String> tbs=new ArrayList<>();
        tbs.add("student");

        return ProcessConfig.builder().designatedTableName(tbs).build();
    }

    /**
     * 设置忽略哪些表不参与生成db文档
     * @return
     */
    private static ProcessConfig getProcessConfigIgnore() {
        ArrayList<String> ignoreTableName = new ArrayList<>();
//        ignoreTableName.add("do_test");
//        ignoreTableName.add("student");
//        ignoreTableName.add("t_null_font");
        ArrayList<String> ignorePrefix = new ArrayList<>();
        // ignorePrefix.add("test_");
        ArrayList<String> ignoreSuffix = new ArrayList<>();
        //  ignoreSuffix.add("_test");
        return ProcessConfig.builder()
                //忽略表名
                .ignoreTableName(ignoreTableName)
                //忽略表前缀
                .ignoreTablePrefix(ignorePrefix)
                //忽略表后缀
                .ignoreTableSuffix(ignoreSuffix)
                .build();
    }

    private static Configuration getScrewConfig(DataSource dataSource, EngineConfig engineConfig, ProcessConfig processConfig) {
        return Configuration.builder()
                //版本
                .version("1.0.0")
                //描述
                .description("数据库设计文档生成")
                //数据源
                .dataSource(dataSource)
                //生成配置
                .engineConfig(engineConfig)
                //生成配置
//                .produceConfig(processConfig)
                .build();
    }

}
