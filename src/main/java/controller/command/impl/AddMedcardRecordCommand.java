package controller.command.impl;

import controller.command.Command;
import controller.util.constants.Attribute;
import controller.util.constants.Page;
import domain.MedcardRecord;
import domain.Patient;
import domain.ProcedureType;
import model.dao.impl.DaoFactoryImpl;
import model.dao.interfaces.DaoFactory;
import model.dao.interfaces.MedcardRecordDao;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AddMedcardRecordCommand implements Command {
    DaoFactory daoFactory = DaoFactoryImpl.getInstance();

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Patient patient = (Patient) req.getSession().getAttribute(Attribute.PATIENT.getAttribute());
        String description = req.getParameter("description");
        ProcedureType procedureType = ProcedureType.valueOf(req.getParameter("procedureType"));

        MedcardRecord record = new MedcardRecord(-1, patient.getId(), procedureType, description);
        MedcardRecordDao recordDao = daoFactory.getMedcardRecordDao();
        boolean succeeded = recordDao.create(record);

        if (succeeded) return Page.PATIENT.getPage();
        req.setAttribute(Attribute.ERROR.getAttribute(),
                "Error creating record for patient " + patient.getName());
        return Page.ERROR.getPage();
    }
}
