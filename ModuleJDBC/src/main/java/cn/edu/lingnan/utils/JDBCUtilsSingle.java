package cn.edu.lingnan.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Map;

/**
 * 单例封装JDBC工具类
 */
public class JDBCUtilsSingle {

    private static String driver = null;
    private static String url = null;
    private static String user = null;
    private static String password = null;
    private static Connection connection = null;
    private static String xmlName = "database.conf.xml";
    private static String xsdName = "database.conf.xsd";
    private static JDBCUtilsSingle jdbcUtilsSingle = null;


    static {
//        System.out.println(Thread.currentThread().getContextClassLoader().getResource("").getPath());
        String basePath = Thread.currentThread().getContextClassLoader().getResource("").getPath();
        String xmlPath = basePath + xmlName;
        String xsdPath = basePath + xsdName;
        if (XmlValidate.validate(xmlPath, xsdPath)) {
            Map<String, String> map = XmlParser.parse(xmlPath);
            driver = map.get("driver");
            url = map.get("url");
            user = map.get("user");
            password = map.get("password");
        }
//        Map<String, String> map = XmlParser.parse(xmlPath);
//        System.out.println(map);
    }

    public static void main(String[] args) {
        System.out.println(JDBCUtilsSingle.getInstance().getConnection());
    }

    private JDBCUtilsSingle() {
        try {
            Class.forName(driver);
            connection = DriverManager.getConnection(url, user, password);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("驱动加载失败");
        }
        System.out.println("single构造器被调用");
    }

    public Connection getConnection() {
        try {
            Connection connection = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public static JDBCUtilsSingle getInstance() {
        if (jdbcUtilsSingle == null) {
            jdbcUtilsSingle = new JDBCUtilsSingle();
        }
        return jdbcUtilsSingle;
    }

}
