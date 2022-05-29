package cn.edu.lingnan.pojo;

public class Salary {
    private String eid;
    private String tid;
    private Double smoney;
    private Integer flag;

    @Override
    public String toString() {
        return "Salary{" +
                "eid='" + eid + '\'' +
                ", tid='" + tid + '\'' +
                ", smoney=" + smoney +
                ", flag=" + flag +
                '}';
    }

    public Integer getFlag() {
        return flag;
    }

    public void setFlag(Integer flag) {
        this.flag = flag;
    }

    public Salary() {

    }

    public Salary(String eid, String tid, Double smoney, Integer flag) {
        this.eid = eid;
        this.tid = tid;
        this.smoney = smoney;
        this.flag = flag;
    }

    public String getEid() {
        return eid;
    }

    public void setEid(String eid) {
        this.eid = eid;
    }

    public String getTid() {
        return tid;
    }

    public void setTid(String tid) {
        this.tid = tid;
    }

    public Double getSmoney() {
        return smoney;
    }

    public void setSmoney(Double smoney) {
        this.smoney = smoney;
    }

}
