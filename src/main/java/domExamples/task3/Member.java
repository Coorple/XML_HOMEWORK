package domExamples.task3;

public class Member extends Person {
    private String position;

    public Member(String name, String position) {
        super(name);
        this.position = position;
    }

    public String getPosition() {
        return position;
    }

    public String toString() {
        return "Сотрудник обслуживающего персонала " + getName() + ", должность: " + position;
    }
}
