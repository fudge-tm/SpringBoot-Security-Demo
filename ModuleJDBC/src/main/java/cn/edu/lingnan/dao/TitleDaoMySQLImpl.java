package cn.edu.lingnan.dao;

import cn.edu.lingnan.pojo.Title;
import cn.edu.lingnan.utils.JDBCUtilsByDruid;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * 职位类dao
 */
public class TitleDaoMySQLImpl extends BasicDAO<Title> implements TitleDao {
    private QueryRunner qrt = new QueryRunner();

    //        由职位id查询职位信息
    public Title querySingleBytid(Class<Title> clazz, String tid) {
        Connection connection = null;
//        String sql="select * from title where tid ='"+tid+"'";
        String sql = "select * from title where tid = ?";
        try {
            connection = JDBCUtilsByDruid.getConnection();
            return qrt.query(connection, sql, new BeanHandler<Title>(clazz), tid);

        } catch (SQLException e) {
            throw new RuntimeException(e); //将编译异常->运行异常 ,抛出
        } finally {
            JDBCUtilsByDruid.close(null, null, connection);
        }

    }

    //        由职位名称查询职位信息
    public List<Title> queryMultiBytname(Class<Title> clazz, String tname) {
        Connection connection = null;
        tname = "%" + tname + "%";
        String sql = "select * from title where tname like ?";
        try {
            connection = JDBCUtilsByDruid.getConnection();
            return qrt.query(connection, sql, new BeanListHandler<Title>(clazz), tname);

        } catch (SQLException e) {
            throw new RuntimeException(e); //将编译异常->运行异常 ,抛出
        } finally {
            JDBCUtilsByDruid.close(null, null, connection);
        }
    }

    //        查询所有职位信息
    public List<Title> queryMultiAllTitle() {
        String sql = "select * from title";
        return queryMulti(sql, Title.class);
    }

    //        更新职位信息
    public boolean updateTitle(Title t) {
        boolean flag = false;
        String sql = "update Title set tname=?,flag=? where tid=?";
        int row = dml(sql, t.getTname(), t.getFlag(), t.getTid());
        if (row > 0)
            flag = true;
        return flag;
    }

    //        插入职位信息
    public boolean insertTitle(Title t) {
        boolean flag = false;
        String sql = "insert into Title VALUES(?,?,?)";
        int row = dml(sql, t.getTid(), t.getTname(), t.getFlag());
        if (row > 0)
            flag = true;
        return flag;
    }

    //        删除职位信息
    public boolean deleteTitle(Title t) {
        boolean flag = false;
        String sql = "delete from Title where tid=?";
        int row = dml(sql, t.getTid());
        if (row > 0)
            flag = true;
        return flag;
    }
}
