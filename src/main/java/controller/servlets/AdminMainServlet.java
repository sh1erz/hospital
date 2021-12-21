package controller.servlets;

import controller.util.constants.JspPath;
import domain.Doctor;
import domain.DoctorSpecialisation;
import domain.Patient;
import model.dao.impl.DaoFactoryImpl;
import model.dao.interfaces.DaoFactory;
import model.dao.interfaces.DoctorDao;
import model.dao.interfaces.PatientDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static controller.util.constants.Attribute.*;

@WebServlet("/admin/main")
public class AdminMainServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        DaoFactory daoFactory = DaoFactoryImpl.getInstance();
        PatientDao patientDao = daoFactory.getPatientDao();
        List<Patient> patient = patientDao.getAllPatientsAlphabetically();
        req.setAttribute(PATIENTS.getAttribute(), patient);
        DoctorDao doctorDao = daoFactory.getDoctorDao();
        List<Doctor> doctors = doctorDao.getAllDoctorsAlphabetically();
        req.getSession().setAttribute(DOCTORS.getAttribute(), doctors);
        req.setAttribute(DOC_SPECIALISATION.getAttribute(), DoctorSpecialisation.values());
        req.getRequestDispatcher(JspPath.ADMIN_MAIN.getPath()).forward(req, resp);
    }
}
