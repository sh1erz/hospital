package model.dao.impl;

import domain.MedcardRecord;
import model.dao.interfaces.MedcardRecordDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MedcardRecordDaoImpl implements MedcardRecordDao {
    private final Connection connection;

    public MedcardRecordDaoImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public boolean create(MedcardRecord medcardRecord) {
        boolean result = false;
        try (PreparedStatement statement = connection.prepareStatement(PatientDaoImpl.SQLPatient.INSERT.QUERY)) {
            statement.setInt(1, medcardRecord.getId());
            statement.setInt(2, medcardRecord.getPatientId());
            statement.setString(3, medcardRecord.getProcedure_type_name());
            statement.setBoolean(4, medcardRecord.getCompletion());
            statement.setString(5, medcardRecord.getDescription());
            result = statement.executeQuery().next();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return result;
    }

    @Override
    public Optional<MedcardRecord> get(Integer id) {
        try (PreparedStatement statement = connection.prepareStatement(SQLMedcardRecord.GET.QUERY)) {
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
        try (PreparedStatement statement = connection.prepareStatement(SQLMedcardRecord.GET_ALL.QUERY)) {
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

    public boolean setCompletion(int medcardId, boolean completion) {
        boolean result = false;
        try (PreparedStatement statement = connection.prepareStatement(SQLMedcardRecord.UPDATE_COMPLETION.QUERY)) {
            statement.setBoolean(1, completion);
            statement.setInt(2, medcardId);
            result = statement.executeQuery().next();
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
                rs.getBoolean("completion"),
                rs.getString("description")
        );
    }

    enum SQLMedcardRecord {
        INSERT("INSERT INTO medcard VALUES ((?),(?),(?),(?),(?))"),
        GET("SELECT * FROM medcard WHERE id = (?)"),
        GET_ALL("SELECT * FROM medcard WHERE patient_id = (?)"),
        UPDATE_COMPLETION("UPDATE medcard SET completion = (?) WHERE id = (?)");
        String QUERY;

        SQLMedcardRecord(String QUERY) {
            this.QUERY = QUERY;
        }
    }

}
