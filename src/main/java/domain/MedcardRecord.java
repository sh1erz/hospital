package domain;

import java.util.Objects;

public class MedcardRecord {
    private final int id;
    private final int patientId;
    private final ProcedureType procedureType;
    private String performer;
    private final String description;

    public MedcardRecord(int id, int patientId, ProcedureType procedureType, String performer, String description) {
        this.id = id;
        this.patientId = patientId;
        this.procedureType = procedureType;
        this.performer = performer;
        this.description = description;
    }

    public MedcardRecord(int id, int patientId, ProcedureType procedureType, String description) {
        this.id = id;
        this.patientId = patientId;
        this.procedureType = procedureType;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public int getPatientId() {
        return patientId;
    }

    public ProcedureType getProcedureType() {
        return procedureType;
    }

    public String getPerformer() {
        return performer;
    }

    public void setPerformer(String performer) {
        this.performer = performer;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MedcardRecord medcardRecord = (MedcardRecord) o;
        return id == medcardRecord.id && patientId == medcardRecord.patientId && Objects.equals(performer, medcardRecord.performer) && procedureType.equals(medcardRecord.procedureType) && description.equals(medcardRecord.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, patientId, procedureType, performer, description);
    }
}

