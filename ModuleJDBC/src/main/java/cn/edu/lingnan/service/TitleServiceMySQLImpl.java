package cn.edu.lingnan.service;

import cn.edu.lingnan.dao.SalaryDaoMySQLImpl;
import cn.edu.lingnan.dao.TitleDaoMySQLImpl;
import cn.edu.lingnan.pojo.Title;

import java.util.List;

/**
 * TitleServiceMySQLImpl实现类
 */
public class TitleServiceMySQLImpl implements TitleService {

    TitleDaoMySQLImpl titleDao = new TitleDaoMySQLImpl();
    SalaryDaoMySQLImpl salaryDao = new SalaryDaoMySQLImpl();

    //        由职位id查询职位信息
    @Override
    public Title querySingleBytid(Class<Title> clazz, String tid) {
        return titleDao.querySingleBytid(clazz, tid);
    }

    public Title queryhisSingleBytid(Class<Title> clazz, String tid) {
        return titleDao.queryhisSingleBytid(Title.class, tid);
    }

    //        由职位名称查询职位信息
    @Override
    public List<Title> queryMultiBytname(Class<Title> clazz, String tname) {
        return titleDao.queryMultiBytname(clazz, tname);
    }

    //    按条件查询
    @Override
    public List<Title> selectByCondition(Title title) {
        return titleDao.selectByCondition(title);
    }

    @Override
    public boolean unexittid(Title title) {
        Title title1 = titleDao.querySingleBytid(Title.class, title.getTid());
        Title title2 = titleDao.queryhisSingleBytid(Title.class, title.getTid());
        if (title1 == null && title2 == null) {
            return true;
        }
        return false;
    }

    //        查询所有职位信息
    @Override
    public List<Title> queryMultiAllTitle() {
        return titleDao.queryMultiAllTitle();
    }

    //        删除职位信息
    @Override
    public boolean deleteTitle(Title t) {
//        boolean flag = false;
//        String sql = "delete from salary where tid=?";
//        int rows1 = salaryDao.dml(sql, t.getTid());
//        if (rows1 > 0)
//            flag = true;
//        return titleDao.deleteTitle(t) && flag;
        return titleDao.deleteTitle(t);
    }

    //        插入职位信息
    @Override
    public boolean insertTitle(Title t) {
        boolean flag = true;
        List<Title> Titles = titleDao.queryMultiAllTitle();

        for (Title title : Titles) {
            if (title.getTid().equals(t.getTid())) {
                flag = false;
            }
        }
        if (flag == false) {
            return flag;
        }
        return titleDao.insertTitle(t);
    }

    //        更新职位信息
    @Override
    public boolean updateTitle(Title t) {
        return titleDao.updateTitle(t);
    }


}
