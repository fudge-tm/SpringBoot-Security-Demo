package cn.edu.lingnan.dao;

import cn.edu.lingnan.pojo.Title;

import java.util.List;

public interface TitleDao {
    public boolean deleteTitle(Title t);

    public boolean insertTitle(Title t);

    public boolean updateTitle(Title t);

    public List<Title> queryMultiAllTitle();

    Title queryhisSingleBytid(Class<Title> clazz, String tid);

    //按条件查询
    List<Title> selectByCondition(Title title);

    public List<Title> queryMultiBytname(Class<Title> clazz, String tname);

    public Title querySingleBytid(Class<Title> clazz, String tid);

}
