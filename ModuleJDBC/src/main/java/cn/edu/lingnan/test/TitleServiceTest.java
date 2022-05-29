package cn.edu.lingnan.test;

import cn.edu.lingnan.pojo.Title;
import cn.edu.lingnan.service.TitleServiceMySQLImpl;

public class TitleServiceTest {
    public static TitleServiceMySQLImpl titleServiceMySQL = new TitleServiceMySQLImpl();

    public static void main(String[] args) {
        Title t = new Title("t001", "CXO", 1);

        //        由职位id查询职位信息
        System.out.println(titleServiceMySQL.querySingleBytid(Title.class, "t01"));

        //        由职位名称查询职位信息
        System.out.println(titleServiceMySQL.queryMultiBytname(Title.class, "CEO"));

        //        查询所有职位信息
        System.out.println(titleServiceMySQL.queryMultiAllTitle());

        //        更新职位信息
        System.out.println(titleServiceMySQL.updateTitle(t));

        //        删除职位信息
        System.out.println(titleServiceMySQL.deleteTitle(t));

        //        插入职位信息
        System.out.println(titleServiceMySQL.insertTitle(t));
    }

}




