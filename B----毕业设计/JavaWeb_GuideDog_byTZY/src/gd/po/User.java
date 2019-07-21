package gd.po;

import java.util.ArrayList;
import java.util.List;

public class User
{
    private int id;
    private String name;
    private String pwd;
    private String role;
    private String tel;
    private String address;
    private List<Guidedog> guidedogs=new ArrayList<Guidedog>();
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getPwd() {
        return pwd;
    }
    public void setPwd(String pwd) {
        this.pwd = pwd;
    }
    public String getRole() {
        return role;
    }
    public void setRole(String role) {
        this.role = role;
    }
    public String getTel() {
        return tel;
    }
    public void setTel(String tel) {
        this.tel = tel;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public List<Guidedog> getGuidedogs() {
        return guidedogs;
    }
    public void setGuidedogs(List<Guidedog> guidedogs) {
        this.guidedogs = guidedogs;
    }
}