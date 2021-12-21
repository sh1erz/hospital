package controller.servlets;

import controller.util.constants.JspPath;
import domain.Doctor;
import domain.MedcardRecord;
import domain.Nurse;
import domain.Patient;
import model.dao.impl.DaoFactoryImpl;
import model.dao.interfaces.DaoFactory;
import model.dao.interfaces.DoctorDao;
import model.dao.interfaces.MedcardRecordDao;
import model.dao.interfaces.NurseDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static controller.util.constants.Attribute.*;

@WebServlet("/doctor/patient")
public class PatientServlet extends HttpServlet {
    DaoFactory daoFactory = DaoFactoryImpl.getInstance();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Patient patient = (Patient) req.getSession().getAttribute(PATIENT.getAttribute());
        MedcardRecordDao medcardRecordDao = daoFactory.getMedcardRecordDao();
        List<MedcardRecord> records = medcardRecordDao.getPatientMedcardRecords(patient.getId());
        req.getSession().setAttribute(PATIENT_RECORDS.getAttribute(),records);

        NurseDao nurseDao = daoFactory.getNurseDao();
        List<Nurse> nurses = nurseDao.getAll();
        req.getSession().setAttribute(NURSES.getAttribute(), nurses);

        DoctorDao doctorDao = daoFactory.getDoctorDao();
        List<Doctor> doctors = doctorDao.getAllDoctorsAlphabetically();
        req.getSession().setAttribute(DOCTORS.getAttribute(), doctors);

        req.getRequestDispatcher(JspPath.PATIENT.getPath()).forward(req,resp);
    }
}
