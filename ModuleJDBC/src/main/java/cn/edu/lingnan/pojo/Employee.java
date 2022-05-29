 package cn.edu.lingnan.pojo;

public class Employee {
    private String eid;
    private String ename;
    private String password;
    private Integer superuser;
    private Integer flag;

    public Employee() {
    }

    public Employee(String eid, String ename, String password, Integer superuser, Integer flag) {
        this.eid = eid;
        this.ename = ename;
        this.password = password;
        this.superuser = superuser;
        this.flag = flag;
    }

    public String getEid() {
        return eid;
    }

    public void setEid(String eid) {
        this.eid = eid;
    }

    public String getEname() {
        return ename;
    }

    public void setEname(String ename) {
        this.ename = ename;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getSuperuser() {
        return superuser;
    }

    public void setSuperuser(Integer superuser) {
        this.superuser = superuser;
    }

    public Integer getFlag() {
        return flag;
    }

    public void setFlag(Integer flag) {
        this.flag = flag;
    }

    @Override
    public String toString() {
        return "employee{" +
                "eid='" + eid + '\'' +
                ", ename='" + ename + '\'' +
                ", password='" + password + '\'' +
                ", superuser=" + superuser +
                ", flag=" + flag +
                '}';
    }
}
