package model.dao.impl;

import domain.MedcardRecord;
import model.dao.connection.ConnectionFactory;
import model.dao.interfaces.MedcardRecordDao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MedcardRecordDaoImpl implements MedcardRecordDao {

    private final ConnectionFactory connectionFactory;
    private static MedcardRecordDaoImpl instance;

    private MedcardRecordDaoImpl(ConnectionFactory connectionFactory) {
        this.connectionFactory = connectionFactory;
    }

    public static MedcardRecordDaoImpl getInstance(ConnectionFactory connectionFactory) {
        MedcardRecordDaoImpl result = instance;
        if (result != null) {
            return result;
        }
        synchronized (MedcardRecordDaoImpl.class) {
            if (instance == null) {
                instance = new MedcardRecordDaoImpl(connectionFactory);
            }
            return instance;
        }
    }

    @Override
    public boolean create(MedcardRecord medcardRecord) {
        boolean result = false;
        try (PreparedStatement statement = connectionFactory.getConnection().prepareStatement(PatientDaoImpl.SQLPatient.INSERT.QUERY)) {
            statement.setInt(1, medcardRecord.getId());
            statement.setInt(2, medcardRecord.getPatientId());
            statement.setString(3, medcardRecord.getProcedure_type_name());
            statement.setString(4, medcardRecord.getPerformer());
            statement.setString(5, medcardRecord.getDescription());
            statement.executeUpdate();
            result = true;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return result;
    }

    @Override
    public Optional<MedcardRecord> get(Integer id) {
        try (PreparedStatement statement = connectionFactory.getConnection().prepareStatement(SQLMedcardRecord.GET.QUERY)) {
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                return Optional.of(retrieveMedcardRecord(rs));
            }
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        return Optional.empty();
    }

    public List<MedcardRecord> getPatientMedcardRecords(int patientId) {
        List<MedcardRecord> list = new ArrayList<>();
        try (PreparedStatement statement = connectionFactory.getConnection().prepareStatement(SQLMedcardRecord.GET_ALL.QUERY)) {
            statement.setInt(1, patientId);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                list.add(retrieveMedcardRecord(rs));
            }
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        return list;
    }

    @Override
    public boolean setPerformer(int medcardId, String performer) {
        boolean result = false;
        try (PreparedStatement statement = connectionFactory.getConnection().prepareStatement(SQLMedcardRecord.UPDATE_PERFORMER.QUERY)) {
            statement.setString(1, performer);
            statement.setInt(2, medcardId);
            statement.executeUpdate();
            result = true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    private MedcardRecord retrieveMedcardRecord(ResultSet rs) throws SQLException {
        return new MedcardRecord(
                rs.getInt("id"),
                rs.getInt("patient_id"),
                rs.getString("procedure_type_name"),
                rs.getString("performer"),
                rs.getString("description")
        );
    }

    enum SQLMedcardRecord {
        INSERT("INSERT INTO medcard VALUES ((?),(?),(?),(?),(?))"),
        GET("SELECT * FROM medcard WHERE id = (?)"),
        GET_ALL("SELECT * FROM medcard WHERE patient_id = (?)"),
        UPDATE_PERFORMER("UPDATE medcard SET performer = (?) WHERE id = (?)");
        final String QUERY;

        SQLMedcardRecord(String QUERY) {
            this.QUERY = QUERY;
        }
    }

}
