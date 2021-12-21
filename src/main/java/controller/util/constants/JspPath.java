package controller.util.constants;

public enum JspPath {
    PATIENT("/jsp/patient.jsp"),
    DOCTOR_MAIN("/jsp/doctor_main.jsp"),
    ADMIN_MAIN("/jsp/admin_main.jsp");

    private final String path;
    JspPath(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }
}
