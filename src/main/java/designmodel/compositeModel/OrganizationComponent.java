package designmodel.compositeModel;

public abstract class OrganizationComponent {

    private Integer level = 1;
    private String name;
    private String desc;

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    protected void add(OrganizationComponent component){
        throw new UnsupportedOperationException();
    }
    protected void remove(OrganizationComponent component){
        throw new UnsupportedOperationException();
    }

    public OrganizationComponent(String name, String desc) {
        super();
        this.name = name;
        this.desc = desc;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    protected abstract void print();
}
