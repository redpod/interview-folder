package gd.po;

import java.util.ArrayList;
import java.util.List;

public class Institution
{
    private int id;
    private String name;
    private List<Dogtype> dogts = new ArrayList<Dogtype>();
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
    public List<Dogtype> getDogts() {
        return dogts;
    }
    public void setDogts(List<Dogtype> dogts) {
        this.dogts = dogts;
    }
}