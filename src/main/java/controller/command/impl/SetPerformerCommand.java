package controller.command.impl;

import controller.command.Command;
import controller.util.constants.Attribute;
import controller.util.constants.Page;
import domain.MedcardRecord;
import model.dao.impl.DaoFactoryImpl;
import model.dao.interfaces.DaoFactory;
import model.dao.interfaces.MedcardRecordDao;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class SetPerformerCommand implements Command {
    DaoFactory daoFactory = DaoFactoryImpl.getInstance();

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        List<MedcardRecord> records = (List<MedcardRecord>) req.getSession().getAttribute(Attribute.PATIENT_RECORDS.getAttribute());
        for (int i = 0; i < records.size(); i++) {
            String performer = req.getParameter("performer" + i).strip();
            if (!performer.isEmpty()) {
                int recordId = records.get(i).getId();
                if (!updatePerformer(recordId, performer)) {
                    req.setAttribute(Attribute.ERROR.getAttribute(),
                            "Error updating performer for record with id" + recordId);
                    return Page.ERROR.getPage();
                }
            }
        }
        String c = req.getContextPath();
        return c;
    }

    private boolean updatePerformer(int recordId, String performer) {
        MedcardRecordDao recordDao = daoFactory.getMedcardRecordDao();
        return recordDao.setPerformer(recordId, performer);
    }
}
