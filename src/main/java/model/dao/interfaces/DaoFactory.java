package model.dao.interfaces;

public interface DaoFactory {
    DoctorDao getDoctorDao();
    PatientDao getPatientDao();
    MedcardRecordDao getMedcardRecordDao();
    NurseDao getNurseDao();

}
