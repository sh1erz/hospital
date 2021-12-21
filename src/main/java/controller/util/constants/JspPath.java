package controller.util.constants;

public enum JspPath {
    PATIENT("/jsp/patient.jsp");

    private final String path;
    JspPath(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }
}
