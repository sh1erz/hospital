package model.dao.interfaces;

import domain.Patient;

import java.util.List;

public interface PatientDao extends Dao<Patient, Integer> {
    List<Patient> getAllPatientsAlphabetically();
    List<Patient> getAllPatientsByBirth(boolean asc);
    boolean setDiagnosis(int patientId, String diagnosis);
}
