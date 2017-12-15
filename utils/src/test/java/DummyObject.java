import java.util.List;

public class DummyObject {
    //class used only for Json testing

    private String name;
    private int age;
    private List<Integer> positions;

    DummyObject(String name, int age, List<Integer> positions) {
        this.name = name;
        this.age = age;
        this.positions = positions;
    }

    public String getName() {
        return name;
    }

    int getAge() {
        return age;
    }


    List<Integer> getPositions() {
        return positions;
    }

}
