package controller.util.constants;

public enum ViewJsp {
    INDEX("/index.jsp"),
    INVALID_LOGIN("/invalid_login"),
    INVALID_PERSON("/invalid_person"),
    DOCTOR_MAIN("/doctor/doctor_main"),
    ADMIN_MAIN("/admin/admin_main");


    private final String page;

    ViewJsp(String page) {
        this.page = page;
    }

    public String getPage() {
        return page;
    }
}
