package model.dao.impl;

import domain.Nurse;
import domain.Patient;
import model.dao.connection.ConnectionFactory;
import model.dao.interfaces.NurseDao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class NurseDaoImpl implements NurseDao {
    private final ConnectionFactory connectionFactory;
    private static NurseDaoImpl instance;

    private NurseDaoImpl(ConnectionFactory connectionFactory) {
        this.connectionFactory = connectionFactory;
    }

    public static NurseDaoImpl getInstance(ConnectionFactory connectionFactory) {
        NurseDaoImpl result = instance;
        if (result != null) {
            return result;
        }
        synchronized (NurseDaoImpl.class) {
            if (instance == null) {
                instance = new NurseDaoImpl(connectionFactory);
            }
            return instance;
        }
    }

    @Override
    public boolean create(Nurse nurse) {
        boolean result = false;
        try (PreparedStatement statement = connectionFactory.getConnection().prepareStatement(SQLNurse.INSERT.QUERY)) {
            statement.setInt(1, nurse.getId());
            statement.setString(2, nurse.getName());
            result = statement.executeQuery().next();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return result;
    }

    @Override
    public Optional<Nurse> get(Integer id) {
        try (PreparedStatement statement = connectionFactory.getConnection().prepareStatement(SQLNurse.GET.QUERY)) {
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                return Optional.of(retrieveNurse(rs));
            }
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        return Optional.empty();
    }

    public List<Nurse> getAllNurses() {
        List<Nurse> list = new ArrayList<>();
        try (PreparedStatement statement = connectionFactory.getConnection().prepareStatement(SQLNurse.GET_ALL.QUERY)) {
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                list.add(retrieveNurse(rs));
            }
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        return list;
    }

    private Nurse retrieveNurse(ResultSet rs) throws SQLException {
        return new Nurse(
                rs.getInt("id"),
                rs.getString("name")
        );
    }

    enum SQLNurse {
        INSERT("INSERT INTO nurse VALUES ((?),(?))"),
        GET("SELECT * FROM nurse WHERE id = (?)"),
        GET_ALL("SELECT * FROM nurse ORDER BY name");
        String QUERY;

        SQLNurse(String QUERY) {
            this.QUERY = QUERY;
        }
    }
}
