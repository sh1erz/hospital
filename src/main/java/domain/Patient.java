package domain;


import java.util.Date;
import java.util.Objects;

public class Patient {
    private final int id;
    private final String name;
    private final Date birth;
    private final int doctorId;
    private String diagnosis;

    public Patient(int id, String name, Date birth, int doctorId) {
        this.id = id;
        this.name = name;
        this.birth = birth;
        this.doctorId = doctorId;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Date getBirth() {
        return birth;
    }

    public int getDoctorId() {
        return doctorId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Patient patient = (Patient) o;
        return id == patient.id && doctorId == patient.doctorId && name.equals(patient.name) && birth.equals(patient.birth);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, birth, doctorId);
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }
}
