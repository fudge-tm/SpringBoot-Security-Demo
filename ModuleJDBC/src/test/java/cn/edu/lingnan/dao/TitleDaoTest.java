package cn.edu.lingnan.dao;

import cn.edu.lingnan.pojo.Title;
import org.junit.Test;

public class TitleDaoTest {
    @Test
    public void querySingleBysidTest() {
        TitleDaoMySQLImpl titleDao = new TitleDaoMySQLImpl();
        System.out.println(titleDao.querySingleBytid(Title.class, "t04"));
    }
}
