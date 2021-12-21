package model.dao.impl;

import domain.Patient;
import model.dao.connection.ConnectionFactory;
import model.dao.interfaces.PatientDao;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PatientDaoImpl implements PatientDao {

    private final ConnectionFactory connectionFactory;
    private static PatientDaoImpl instance;

    private PatientDaoImpl(ConnectionFactory connectionFactory) {
        this.connectionFactory = connectionFactory;
    }

    public static PatientDaoImpl getInstance(ConnectionFactory connectionFactory) {
        PatientDaoImpl result = instance;
        if (result != null) {
            return result;
        }
        synchronized (PatientDaoImpl.class) {
            if (instance == null) {
                instance = new PatientDaoImpl(connectionFactory);
            }
            return instance;
        }
    }

    @Override
    public boolean create(Patient patient) {
        boolean result = false;
        try (PreparedStatement statement = connectionFactory.getConnection().prepareStatement(SQLPatient.INSERT.QUERY)) {
            statement.setString(1, patient.getName());
            statement.setDate(2, new Date(patient.getBirth().getTime()));
            statement.setInt(3, patient.getDoctorId());
            statement.executeUpdate();
            result = true;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return result;
    }

    @Override
    public Optional<Patient> get(Integer id) {
        try (PreparedStatement statement = connectionFactory.getConnection().prepareStatement(SQLPatient.GET.QUERY)) {
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                return Optional.of(retrievePatient(rs));
            }
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public List<Patient> getAllDoctorPatients(int doctorId) {
        List<Patient> list = new ArrayList<>();
        try (PreparedStatement statement = connectionFactory.getConnection().prepareStatement(SQLPatient.GET_BY_DOCTOR.QUERY)) {
            statement.setInt(1, doctorId);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                list.add(retrievePatient(rs));
            }
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        return list;
    }

    public List<Patient> getAllPatientsAlphabetically() {
        List<Patient> list = new ArrayList<>();
        try (PreparedStatement statement = connectionFactory.getConnection().prepareStatement(SQLPatient.GET_ALL.QUERY)) {
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                list.add(retrievePatient(rs));
            }
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        return list;
    }

    public List<Patient> getAllPatientsByBirth(boolean asc) {
        List<Patient> list = new ArrayList<>();
        try (PreparedStatement statement = connectionFactory.getConnection().prepareStatement(SQLPatient.GET_BY_BIRTH.QUERY)) {
            if (asc) {
                statement.setString(1, "ASC");
            } else {
                statement.setString(1, "DESC");
            }
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                list.add(retrievePatient(rs));
            }
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        return list;
    }

    public boolean setDiagnosis(int patientId, String diagnosis) {
        boolean result = false;
        try (PreparedStatement statement = connectionFactory.getConnection().prepareStatement(SQLPatient.UPDATE_DIAGNOSIS.QUERY)) {
            statement.setString(1, diagnosis);
            statement.setInt(2, patientId);
            statement.executeUpdate();
            result = true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    private Patient retrievePatient(ResultSet rs) throws SQLException {
        return new Patient(
                rs.getInt("id"),
                rs.getString("name"),
                new java.util.Date(rs.getDate("birth").getTime()),
                rs.getInt("doctor_id"),
                rs.getString("diagnosis")
        );
    }


    enum SQLPatient {
        INSERT("INSERT INTO patient (name,birth,doctor_id) VALUES (?,?,?)"),
        GET("SELECT * FROM patient WHERE id = (?)"),
        GET_BY_DOCTOR("SELECT * FROM patient WHERE doctor_id = (?)"),
        GET_ALL("SELECT * FROM patient ORDER BY name"),
        GET_BY_BIRTH("SELECT * FROM patient ORDER BY birth (?)"),
        UPDATE_DIAGNOSIS("UPDATE patient SET diagnosis = (?) WHERE id = (?)");
        final String QUERY;

        SQLPatient(String QUERY) {
            this.QUERY = QUERY;
        }
    }
}
