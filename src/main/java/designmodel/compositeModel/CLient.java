package designmodel.compositeModel;

import com.sun.xml.internal.ws.util.NoCloseOutputStream;

/**
 * 组合模式
 */
public class CLient {

    public static void main(String[] args) {
        University university = new University("清华大学", "985名校");

        College college = new College("建工学院","清华大学");
        College college2 = new College("外语学院","清华");

        university.add(college);
        university.add(college2);

        Leaf leaf = new Leaf("土木工程","1");
        Leaf leaf2 = new Leaf("流体力学","2");
        college.add(leaf);
        college.add(leaf2);

        Leaf leaf3 = new Leaf("英语学院","2");
        Leaf leaf4 = new Leaf("法语学院","2");
        college2.add(leaf3);
        college2.add(leaf4);

        university.print();

    }
}
