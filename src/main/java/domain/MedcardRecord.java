package domain;

import java.util.Objects;

public class MedcardRecord {
    private final int id;
    private final int patientId;
    private final String procedure_type_name;
    private boolean completion;
    private final String description;

    public MedcardRecord(int id, int patientId, String procedure_type_name, boolean completion, String description) {
        this.id = id;
        this.patientId = patientId;
        this.procedure_type_name = procedure_type_name;
        this.completion = completion;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public int getPatientId() {
        return patientId;
    }

    public String getProcedure_type_name() {
        return procedure_type_name;
    }

    public boolean getCompletion() {
        return completion;
    }

    public void setCompletion(boolean completion) {
        this.completion = completion;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MedcardRecord medcardRecord = (MedcardRecord) o;
        return id == medcardRecord.id && patientId == medcardRecord.patientId && completion == medcardRecord.completion && procedure_type_name.equals(medcardRecord.procedure_type_name) && description.equals(medcardRecord.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, patientId, procedure_type_name, completion, description);
    }
}

