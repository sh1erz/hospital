package model.dao.interfaces;

import domain.MedcardRecord;

import java.sql.ResultSet;
import java.util.List;

public interface MedcardRecordDao extends Dao<MedcardRecord, Integer> {
    List<MedcardRecord> getPatientMedcardRecords(int patientId);
    boolean setPerformer(int medcardId, String performer);
}
