package model.dao.impl;

import model.dao.connection.ConnectionFactoryImpl;
import model.dao.interfaces.*;

public class DaoFactoryImpl implements DaoFactory {

    @Override
    public DoctorDao getDoctorDao() {
        return DoctorDaoImpl.getInstance(ConnectionFactoryImpl.getInstance());
    }

    @Override
    public PatientDao getPatientDao() {
        return PatientDaoImpl.getInstance(ConnectionFactoryImpl.getInstance());
    }

    @Override
    public MedcardRecordDao getMedcardRecordDao() {
        return MedcardRecordDaoImpl.getInstance(ConnectionFactoryImpl.getInstance());
    }

    @Override
    public NurseDao getNurseDao() {
        return NurseDaoImpl.getInstance(ConnectionFactoryImpl.getInstance());
    }
}
