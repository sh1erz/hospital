package controller.util.constants;

public enum Page {
    INDEX("/index.jsp"),
    INVALID_LOGIN("/invalid_login"),
    INVALID_PERSON("/invalid_person"),
    DOCTOR_MAIN("/doctor/main"),
    ADMIN_MAIN("/admin/main"),
    PATIENT("/doctor/patient"),
    ERROR("/error");


    private final String page;

    Page(String page) {
        this.page = page;
    }

    public String getPage() {
        return page;
    }
}
