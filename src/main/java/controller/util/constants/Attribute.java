package controller.util.constants;

public enum Attribute {
    USER_ADMIN("userAdmin"),
    USER_DOCTOR("userDoctor"),
    DOCTORS("doctors"),
    PATIENTS("patients"),
    NURSES("nurses"),
    DOC_SPECIALISATION("specialisations"),
    PATIENT("patient"),
    ERROR("error"),
    PATIENT_RECORDS("records"),
    PROCEDURE_TYPES("procedureTypes");


    private final String attribute;

    Attribute(String attribute) {
        this.attribute = attribute;
    }

    public String getAttribute() {
        return attribute;
    }
}
