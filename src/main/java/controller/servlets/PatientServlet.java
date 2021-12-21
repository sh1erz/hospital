package controller.servlets;

import controller.util.constants.JspPath;
import domain.MedcardRecord;
import domain.Patient;
import model.dao.impl.DaoFactoryImpl;
import model.dao.interfaces.MedcardRecordDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static controller.util.constants.Attribute.PATIENT;
import static controller.util.constants.Attribute.PATIENT_RECORDS;

@WebServlet("/doctor/patient")
public class PatientServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Patient patient = (Patient) req.getSession().getAttribute(PATIENT.getAttribute());
        MedcardRecordDao medcardRecordDao = DaoFactoryImpl.getInstance().getMedcardRecordDao();
        List<MedcardRecord> records = medcardRecordDao.getPatientMedcardRecords(patient.getId());
        req.getSession().setAttribute(PATIENT_RECORDS.getAttribute(),records);
        req.getRequestDispatcher(JspPath.PATIENT.getPath()).forward(req,resp);
    }
}
