package cn.edu.lingnan.dao;

import cn.edu.lingnan.pojo.Title;
import org.apache.commons.dbutils.QueryRunner;

import java.util.List;

/**
 * 职位类dao
 */
public class TitleDaoMySQLImpl extends BasicDAO<Title> implements TitleDao {
    private QueryRunner qrt = new QueryRunner();

    //        由职位id查询职位信息
    @Override
    public Title querySingleBytid(Class<Title> clazz, String tid) {

//        String sql="select * from title where tid ='"+tid+"'";
        String sql = "select * from title where tid = ?";
        return querySingle(sql, clazz, tid);

    }

    @Override
    public Title queryhisSingleBytid(Class<Title> clazz, String tid) {

//        String sql="select * from title where tid ='"+tid+"'";
        String sql = "select * from history_title where tid = ?";
        return querySingle(sql, clazz, tid);

    }

    //按条件查询
    @Override
    public List<Title> selectByCondition(Title title) {
        String sql = "select * from title where 1=1";
        if (title != null) {
            if ((title.getTid() != null) && (title.getTid().length() > 0)) {
                sql = sql + " and tid like '%" + title.getTid() + "%'";
            }
            if ((title.getTname() != null) && (title.getTname().length() > 0)) {
                sql = sql + " and tname like '%" + title.getTname() + "%'";
            }
        }
        return queryMulti(sql, Title.class);
    }

    //        由职位名称查询职位信息
    public List<Title> queryMultiBytname(Class<Title> clazz, String tname) {
        tname = "%" + tname + "%";
        String sql = "select * from title where tname like ?";
        return queryMulti(sql, clazz, tname);

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
