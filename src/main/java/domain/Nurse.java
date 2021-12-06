package domain;

import java.util.Objects;

public class Nurse {
    private final int id;
    private final String name;

    public Nurse(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Nurse nurse = (Nurse) o;
        return id == nurse.id && name.equals(nurse.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
