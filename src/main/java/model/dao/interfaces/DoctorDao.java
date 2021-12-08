package model.dao.interfaces;

import domain.Doctor;
import domain.DoctorSpecialisation;

import java.util.List;

public interface DoctorDao extends Dao<Doctor,Integer> {
    List<Doctor> getAllDoctorsAlphabetically();
    List<Doctor> getDoctorsBySpecialisation(DoctorSpecialisation ds);
    List<Doctor> getDoctorsByPatientsAmount();
    String getPassword(String login);
}
