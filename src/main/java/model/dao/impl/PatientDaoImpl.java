package model.dao.impl;

import domain.Patient;
import model.dao.interfaces.PatientDao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PatientDaoImpl implements PatientDao {

    private final Connection connection;

    public PatientDaoImpl(Connection connection) {
        this.connection = connection;
    }


    @Override
    public boolean create(Patient patient) {
        boolean result = false;
        try (PreparedStatement statement = connection.prepareStatement(SQLPatient.INSERT.QUERY)) {
            statement.setInt(1, patient.getId());
            statement.setString(2, patient.getName());
            statement.setDate(3, new Date(patient.getBirth().getTime()));
            statement.setInt(4, patient.getDoctorId());
            result = statement.executeQuery().next();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return result;
    }

    @Override
    public Optional<Patient> get(Integer id) {
        try (PreparedStatement statement = connection.prepareStatement(SQLPatient.GET.QUERY)) {
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

    public List<Patient> getAllPatientsAlphabetically() {
        List<Patient> list = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(SQLPatient.GET_ALL.QUERY)) {
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
        try (PreparedStatement statement = connection.prepareStatement(SQLPatient.GET_BY_BIRTH.QUERY)) {
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
        try (PreparedStatement statement = connection.prepareStatement(SQLPatient.UPDATE_DIAGNOSIS.QUERY)) {
            statement.setString(1, diagnosis);
            statement.setInt(2, patientId);
            result = statement.executeQuery().next();
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
                rs.getInt("doctor_id")
        );
    }


    enum SQLPatient {
        INSERT("INSERT INTO patient VALUES ((?),(?),(?),(?))"),
        GET("SELECT * FROM patient WHERE id = (?)"),
        GET_ALL("SELECT * FROM patient ORDER BY name"),
        GET_BY_BIRTH("SELECT * FROM patient ORDER BY birth (?)"),
        UPDATE_DIAGNOSIS("UPDATE patient SET diagnosis = (?) WHERE id = (?)");
        String QUERY;

        SQLPatient(String QUERY) {
            this.QUERY = QUERY;
        }
    }
}
