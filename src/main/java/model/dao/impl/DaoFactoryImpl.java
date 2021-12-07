package model.dao.impl;

import model.dao.connection.ConnectionFactoryImpl;
import model.dao.interfaces.*;

public class DaoFactoryImpl implements DaoFactory {

    private static volatile DaoFactoryImpl instance;

    private DaoFactoryImpl() {
    }

    public static DaoFactoryImpl getInstance() {
        DaoFactoryImpl result = instance;
        if (result != null) {
            return result;
        }
        synchronized (DaoFactoryImpl.class) {
            if (instance == null) {
                instance = new DaoFactoryImpl();
            }
            return instance;
        }
    }

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
