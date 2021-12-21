package model.dao.interfaces;

import domain.Nurse;

import java.util.List;

public interface NurseDao extends Dao<Nurse, Integer> {
    List<Nurse> getAll();
}
