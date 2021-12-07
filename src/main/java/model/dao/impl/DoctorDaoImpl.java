package model.dao.impl;

import domain.Doctor;
import domain.DoctorSpecialisation;
import model.dao.connection.ConnectionFactory;
import model.dao.interfaces.DoctorDao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DoctorDaoImpl implements DoctorDao {

    private final ConnectionFactory connectionFactory;
    private static DoctorDaoImpl instance;

    private DoctorDaoImpl(ConnectionFactory connectionFactory) {
        this.connectionFactory = connectionFactory;
    }

    public static DoctorDaoImpl getInstance(ConnectionFactory connectionFactory) {
        DoctorDaoImpl result = instance;
        if (result != null) {
            return result;
        }
        synchronized (DoctorDaoImpl.class) {
            if (instance == null) {
                instance = new DoctorDaoImpl(connectionFactory);
            }
            return instance;
        }
    }

    @Override
    public boolean create(Doctor doctor) {
        boolean result = false;
        try (PreparedStatement statement = connectionFactory.getConnection().prepareStatement(SQLDoctor.INSERT.QUERY)) {
            statement.setInt(1, doctor.getId());
            statement.setString(2, doctor.getName());
            statement.setString(3, doctor.getDoctorSpecialisationName().name());
            result = statement.executeQuery().next();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return result;
    }

    @Override
    public Optional<Doctor> get(Integer id) {
        try (PreparedStatement statement = connectionFactory.getConnection().prepareStatement(SQLDoctor.GET.QUERY)) {
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                return Optional.of(retrieveDoctor(rs));
            }
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        return Optional.empty();
    }


    public List<Doctor> getAllDoctorsAlphabetically() {
        List<Doctor> list = new ArrayList<>();
        try (PreparedStatement statement = connectionFactory.getConnection().prepareStatement(SQLDoctor.GET_ALL.QUERY)) {
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                list.add(retrieveDoctor(rs));
            }
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        return list;
    }

    public List<Doctor> getDoctorsBySpecialisation(DoctorSpecialisation ds) {
        List<Doctor> list = new ArrayList<>();
        try (PreparedStatement statement = connectionFactory.getConnection().prepareStatement(SQLDoctor.GET_BY_CATEGORY.QUERY)) {
            statement.setString(1, ds.name());
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                list.add(retrieveDoctor(rs));
            }
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        return list;
    }

    public List<Doctor> getDoctorsByPatientsAmount() {
        List<Doctor> list = new ArrayList<>();
        try (PreparedStatement statement = connectionFactory.getConnection().prepareStatement(SQLDoctor.GET_BY_PATIENTS.QUERY)) {
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                list.add(retrieveDoctor(rs));
            }
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        return list;
    }

    private Doctor retrieveDoctor(ResultSet rs) throws SQLException {
        return new Doctor(
                rs.getInt("id"),
                rs.getString("name"),
                DoctorSpecialisation.valueOf(rs.getString("doctor_specialisation_name"))
        );
    }

    enum SQLDoctor {
        INSERT("INSERT INTO doctor VALUES ((?),(?),(?))"),
        GET("SELECT * FROM doctor WHERE id = (?)"),
        GET_ALL("SELECT * FROM doctor ORDER BY name"),
        GET_BY_CATEGORY("SELECT * FROM doctor WHERE doctor_specialisation_name = (?) ORDER BY name"),
        GET_BY_PATIENTS("USE hospital;\n" +
                "SELECT doctor.*, \n" +
                "(SELECT count(*) FROM patient\n" +
                "WHERE patient.doctor_id = doctor.id) as patients\n" +
                "FROM hospital.doctor\n" +
                "ORDER BY patients DESC;");
        String QUERY;

        SQLDoctor(String QUERY) {
            this.QUERY = QUERY;
        }
    }
}

