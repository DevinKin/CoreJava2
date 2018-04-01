package objectStream;

import java.io.Serializable;

public class Manager extends Employee {
    private Employee secretary;
    public void setSecretary(Employee secretary) {
        this.secretary = secretary;
    }

    public Manager(String name, double salary, int year, int month, int day) {
        super(name, salary, year, month, day);
        secretary = null;
    }

    @Override
    public String toString() {
        return super.toString() + ", secretary=" + secretary;
    }
}
