package cn.edu.lingnan.service;

import cn.edu.lingnan.pojo.Title;

import java.util.List;

/**
 * TitleService接口
 */
public interface TitleService {

    public Title querySingleBytid(Class<Title> clazz, String tid);

    public List<Title> queryMultiBytname(Class<Title> clazz, String tname);

    public List<Title> queryMultiAllTitle();

    public boolean deleteTitle(Title t);

    public boolean insertTitle(Title t);

    public boolean updateTitle(Title t);
}
