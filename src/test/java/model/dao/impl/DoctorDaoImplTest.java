package model.dao.impl;

import model.dao.interfaces.DoctorDao;
import org.junit.Test;

import static org.junit.Assert.*;

public class DoctorDaoImplTest {

    @Test
    public void getPassword() {
        DoctorDao docd = DaoFactoryImpl.getInstance().getDoctorDao();
        assertEquals("abab", docd.getPassword("sergienko_serhiy"));

    }
}