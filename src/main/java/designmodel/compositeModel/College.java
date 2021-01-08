package designmodel.compositeModel;


import java.util.ArrayList;
import java.util.List;

public class College extends OrganizationComponent {

    private List<OrganizationComponent> list = new ArrayList<>();

    public College(String name, String desc) {
        super(name, desc);
    }

    @Override
    protected void add(OrganizationComponent component) {
        component.setLevel(1);
        list.add(component);
    }

    @Override
    protected void remove(OrganizationComponent component) {
        list.remove(component);
    }

    @Override
    protected void print() {
        System.out.println("-------"+getName()+"---------");
        for (OrganizationComponent organizationComponent : list) {
            organizationComponent.print();
        }
    }
}
