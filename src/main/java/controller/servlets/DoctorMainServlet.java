package controller.servlets;

import controller.util.constants.JspPath;
import domain.Doctor;
import domain.Patient;
import model.dao.impl.DaoFactoryImpl;
import model.dao.interfaces.DaoFactory;
import model.dao.interfaces.PatientDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static controller.util.constants.Attribute.PATIENTS;
import static controller.util.constants.Attribute.USER_DOCTOR;

@WebServlet("/doctor/main")
public class DoctorMainServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Doctor doctor = (Doctor) req.getSession().getAttribute(USER_DOCTOR.getAttribute());
        DaoFactory daoFactory = DaoFactoryImpl.getInstance();
        PatientDao patientDao = daoFactory.getPatientDao();
        List<Patient> patient = patientDao.getAllDoctorPatients(doctor.getId());
        req.setAttribute(PATIENTS.getAttribute(), patient);
        req.getRequestDispatcher(JspPath.DOCTOR_MAIN.getPath()).forward(req, resp);
    }
}
