package designmodel.compositeModel;

import java.util.ArrayList;
import java.util.List;

public class University extends OrganizationComponent {

    List<OrganizationComponent> organizationComponentLists = new ArrayList<>();

    public University(String name, String desc) {
        super(name, desc);
    }

    @Override
    protected void add(OrganizationComponent organizationComponent) {
        organizationComponent.setLevel(3);
        organizationComponentLists.add(organizationComponent);
    }

    @Override
    protected void remove(OrganizationComponent organizationComponent) {
        organizationComponentLists.remove(organizationComponent);
    }

    @Override
    protected void print() {
        System.out.println("-------"+getName()+"--------------------");
        for (OrganizationComponent organizationComponentList : organizationComponentLists) {
            organizationComponentList.print();
        }
    }
}
