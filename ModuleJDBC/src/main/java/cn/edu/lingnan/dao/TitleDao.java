package cn.edu.lingnan.dao;

import cn.edu.lingnan.pojo.Title;

import java.util.List;

public interface TitleDao {
    public boolean deleteTitle(Title t);

    public boolean insertTitle(Title t);

    public boolean updateTitle(Title t);

    public List<Title> queryMultiAllTitle();

    public List<Title> queryMultiBytname(Class<Title> clazz, String tname);

    public Title querySingleBytid(Class<Title> clazz, String tid);

}
