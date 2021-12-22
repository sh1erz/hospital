package controller.servlets;

import controller.util.constants.JspPath;
import controller.util.constants.Page;
import domain.*;
import model.dao.impl.DaoFactoryImpl;
import model.dao.interfaces.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

import static controller.util.constants.Attribute.*;

@WebServlet("/doctor/patient")
public class PatientServlet extends HttpServlet {
    DaoFactory daoFactory = DaoFactoryImpl.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Patient patient = (Patient) req.getSession().getAttribute(PATIENT.getAttribute());
        PatientDao patientDao = daoFactory.getPatientDao();
        Optional<Patient> upToDatePatient = patientDao.get(patient.getId());
        if (upToDatePatient.isEmpty()) {
            req.setAttribute(ERROR.getAttribute(), "Error fetching patient");
            req.getRequestDispatcher(Page.ERROR.getPage()).forward(req, resp);
            return;
        }
        patient = upToDatePatient.get();
        req.getSession().setAttribute(PATIENT.getAttribute(),patient);
        setRecords(req, patient);
        setDoctors(req);
        setNurses(req);
        setProcedureTypesAttribute(req);

        req.getRequestDispatcher(JspPath.PATIENT.getPath()).forward(req, resp);
    }

    private void setProcedureTypesAttribute(HttpServletRequest req){
        req.getSession().setAttribute(PROCEDURE_TYPES.getAttribute(), ProcedureType.values());
    }

    private void setRecords(HttpServletRequest req, Patient patient) {
        MedcardRecordDao medcardRecordDao = daoFactory.getMedcardRecordDao();
        List<MedcardRecord> records = medcardRecordDao.getPatientMedcardRecords(patient.getId());
        req.getSession().setAttribute(PATIENT_RECORDS.getAttribute(), records);
    }

    private void setDoctors(HttpServletRequest req) {
        DoctorDao doctorDao = daoFactory.getDoctorDao();
        List<Doctor> doctors = doctorDao.getAllDoctorsAlphabetically();
        req.getSession().setAttribute(DOCTORS.getAttribute(), doctors);
    }

    private void setNurses(HttpServletRequest req) {
        NurseDao nurseDao = daoFactory.getNurseDao();
        List<Nurse> nurses = nurseDao.getAll();
        req.getSession().setAttribute(NURSES.getAttribute(), nurses);
    }

}
