package model.dao.interfaces;

import domain.Doctor;
import domain.DoctorSpecialisation;

import java.util.List;
import java.util.Optional;

public interface DoctorDao extends Dao<Doctor,Integer> {
    Optional<Doctor> get(String login);
    List<Doctor> getAllDoctorsAlphabetically();
    List<Doctor> getDoctorsBySpecialisation(DoctorSpecialisation ds);
    List<Doctor> getDoctorsByPatientsAmount();
    String getPassword(String login);
}
