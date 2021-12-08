package model.dao.impl;

import domain.Nurse;
import model.dao.interfaces.NurseDao;
import org.junit.Test;

import java.util.Optional;

import static org.junit.Assert.*;

public class NurseDaoImplTest {

    @Test
    public void get() {
        NurseDao nd = DaoFactoryImpl.getInstance().getNurseDao();
        Optional<Nurse> nurse = nd.get(1);
        assertTrue(nurse.isPresent());
        System.out.printf("id: %d, name: %s", nurse.get().getId(), nurse.get().getName());
    }
}