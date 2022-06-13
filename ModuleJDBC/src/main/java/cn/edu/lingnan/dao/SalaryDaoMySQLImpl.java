package cn.edu.lingnan.dao;

import cn.edu.lingnan.pojo.Salary;
import org.apache.commons.dbutils.QueryRunner;

import java.util.List;

/**
 * 工资类dao
 */
public class SalaryDaoMySQLImpl extends BasicDAO<Salary> implements SalaryDao {
    private QueryRunner qrs = new QueryRunner();

    //由员工id和职位id查询工资
    @Override
    public Salary querySingleBysid(Class<Salary> clazz, Salary salary) {

        String sql = "select * from salary where eid= ? and tid= ?";

        return querySingle(sql, clazz, salary.getEid(), salary.getTid());

    }

    @Override
    public Salary queryhisSingleBysid(Class<Salary> clazz, Salary salary) {

        String sql = "select * from history_salary where eid= ? and tid= ?";

        return querySingle(sql, clazz, salary.getEid(), salary.getTid());

    }

    @Override
    public Salary querySingleByeid(Class<Salary> clazz, Salary salary) {
        String sql = "select * from salary where eid= ?";
        return querySingle(sql, clazz, salary.getEid());

    }

    @Override
    public Salary querySingleBytid(Class<Salary> clazz, Salary salary) {
        String sql = "select * from salary where tid= ?";
        return querySingle(sql, clazz, salary.getTid());

    }

    @Override
    public Salary queryhisSingleByeid(Class<Salary> clazz, Salary salary) {
        String sql = "select * from history_salary where eid= ?";
        return querySingle(sql, clazz, salary.getEid());

    }

    @Override
    public Salary queryhisSingleBytid(Class<Salary> clazz, Salary salary) {
        String sql = "select * from history_salary where tid= ?";
        return querySingle(sql, clazz, salary.getTid());

    }


    //按条件查询
    @Override
    public List<Salary> selectByCondition(Salary salary) {
        String sql = "select * from salary where 1=1";
        if (salary != null) {
            if ((salary.getEid() != null) && (salary.getEid().length() > 0)) {
                sql = sql + " and eid like '%" + salary.getEid() + "%'";
            }
            if ((salary.getTid() != null) && (salary.getTid().length() > 0)) {
                sql = sql + " and tid like '%" + salary.getTid() + "%'";
            }
        }
        return queryMulti(sql, Salary.class);
    }

    //        由员工id查询工资
    public List<Salary> queryMultiByeid(Class<Salary> clazz, String eid) {

        String sql = "select * from salary where eid = ?";
        return queryMulti(sql, clazz, eid);
    }

    //        查询所有工资
    public List<Salary> queryMultiAllSalary() {
        String sql = "select * from salary";
        return queryMulti(sql, Salary.class);
    }

    //        更新工资表
    public boolean updateSalary(Salary s) {
        boolean flag = false;
        String sql = "update salary set smoney=?,flag=? where eid=? and tid=?";
        int row = dml(sql, s.getSmoney(), s.getFlag(), s.getEid(), s.getTid());
        if (row > 0)
            flag = true;
        return flag;
    }

    //        插入工资记录
    public boolean insertSalary(Salary s) {
        boolean flag = false;
        String sql = "insert into salary VALUES(?,?,?,?)";
        int row = dml(sql, s.getEid(), s.getTid(), s.getSmoney(), s.getFlag());
        if (row > 0)
            flag = true;
        return flag;
    }

    //       删除工资记录
    public boolean deleteSalary(Salary s) {
        boolean flag = false;
        String sql = "delete from salary where eid=? and tid=?";
        int row = dml(sql, s.getEid(), s.getTid());
        if (row > 0)
            flag = true;
        return flag;
    }

}
