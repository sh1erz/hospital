package model.dao.interfaces;

import domain.Doctor;
import domain.Patient;

import java.util.List;

public interface PatientDao extends Dao<Patient, Integer> {
    List<Patient> getAllDoctorPatients(int doctorId);
    List<Patient> getAllPatientsAlphabetically();
    List<Patient> getAllPatientsByBirth(boolean asc);
    boolean setDiagnosis(int patientId, String diagnosis);
}
