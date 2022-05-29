package cn.edu.lingnan.pojo;

public class Title {
    private String tid;
    private String tname;
    private Integer flag;

    public Title(){

    }

    public Title(String tid, String tname, Integer flag) {
        this.tid = tid;
        this.tname = tname;
        this.flag = flag;
    }

    public String getTid() {
        return tid;
    }

    public void setTid(String tid) {
        this.tid = tid;
    }

    public String getTname() {
        return tname;
    }

    public void setTname(String tname) {
        this.tname = tname;
    }

    public Integer getFlag() {
        return flag;
    }

    public void setFlag(Integer flag) {
        this.flag = flag;
    }

    @Override
    public String toString() {
        return "title{" +
                "tid='" + tid + '\'' +
                ", tname='" + tname + '\'' +
                ", flag=" + flag +
                '}';
    }
}
