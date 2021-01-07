package designmodel.compositeModel;

public class Leaf extends OrganizationComponent {

    public Leaf(String name, String desc) {
        super(name, desc);
    }

    @Override
    protected void print() {
        System.out.println(this.getName());
    }
}
