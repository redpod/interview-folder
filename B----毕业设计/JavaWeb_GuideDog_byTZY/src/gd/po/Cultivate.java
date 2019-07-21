package gd.po;

public class Cultivate
{
    private int id;
    private int guidedogId;
    private int institutionId;
    private String institutionName;
    private String cultivatedate;
    private String description;
    private String treatment;

    public String getInstitutionName()
    {
        return institutionName;
    }
    public void setInstitutionName(String institutionName)
    {
        this.institutionName = institutionName;
    }
    public int getId()
    {
        return id;
    }
    public void setId(int id)
    {
        this.id = id;
    }
    public int getGuidedogId() {
        return guidedogId;
    }
    public void setGuidedogId(int guidedogId)
    {
        this.guidedogId = guidedogId;
    }
    public int getInstitutionId()
    {
        return institutionId;
    }
    public void setInstitutionId(int institutionId)
    {
        this.institutionId = institutionId;
    }
    public String getCultivatedate()
    {
        return cultivatedate;
    }
    public void setCultivatedate(String cultivatedate)
    {
        this.cultivatedate = cultivatedate;
    }
    public String getDescription()
    {
        return description;
    }
    public void setDescription(String description)
    {
        this.description = description;
    }
    public String getTreatment()
    {
        return treatment;
    }
    public void setTreatment(String treatment)
    {
        this.treatment = treatment;
    }
}