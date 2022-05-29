package cn.edu.lingnan.utils;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

/**
 * Druid工具类
 */
public class JDBCUtilsByDruid {
    private static DataSource ds;

    static {
        Properties properties = new Properties();
        try {
//            properties.load(new FileInputStream("D:\\idea\\JavaWeb202001\\ModuleJDBC\\src\\main\\resources\\druid.properties"));
//            properties.load(new FileInputStream("src\\main\\resources\\druid.properties"));
            InputStream resourceAsStream = JDBCUtilsByDruid.class.getResourceAsStream("/druid.properties");
            properties.load(resourceAsStream);
            ds = DruidDataSourceFactory.createDataSource(properties);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static Connection getConnection() throws SQLException {
        return ds.getConnection();
    }

    //    在数据库连接池技术中关闭连接，close仅代表把使用的Connection对象返回连接池
    public static void close(ResultSet resultSet, Statement statement, Connection connection) {
        try {
            if (resultSet != null) {
                resultSet.close();
            }
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
