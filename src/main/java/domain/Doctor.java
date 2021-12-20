package domain;

import java.util.Objects;

public class Doctor {
    private final int id;
    private final String name;
    private final DoctorSpecialisation doctorSpecialisationName;
    private final String login;
    private final String password;

    public Doctor(int id, String name, DoctorSpecialisation doctorSpecialisationName, String login, String password) {
        this.id = id;
        this.name = name;
        this.doctorSpecialisationName = doctorSpecialisationName;
        this.login = login;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public DoctorSpecialisation getDoctorSpecialisationName() {
        return doctorSpecialisationName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Doctor doctor = (Doctor) o;
        return id == doctor.id && name.equals(doctor.name) && doctorSpecialisationName.equals(doctor.doctorSpecialisationName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, doctorSpecialisationName);
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }
}
