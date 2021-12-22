package controller.command.impl;

import controller.command.Command;
import controller.util.constants.SortDoctor;
import controller.util.constants.SortPatient;
import domain.Doctor;
import domain.Patient;
import model.exceptions.InvalidSortedType;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

import static controller.util.constants.Attribute.*;

public class SortCommand implements Command {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String sortWho = req.getParameter("sortWho");

        if (sortWho.equals(DOCTORS.name())) {
            List<Doctor> doctors = (List<Doctor>) req.getSession(false).getAttribute(DOCTORS.getAttribute());
            sortDoctors(req, doctors);
            req.getSession(false).setAttribute(SORTED_DOCTOR.getAttribute(), doctors);
        } else if (sortWho.equals(PATIENTS.name())) {
            List<Patient> patients = (List<Patient>) req.getSession(false).getAttribute(PATIENTS.getAttribute());
            sortPatients(req,patients);
            req.getSession(false).setAttribute(SORTED_PATIENT.getAttribute(), patients);
        } else throw new InvalidSortedType("Sorted type neither doctor nor patient");
        return req.getParameter("uri");
    }

    private void sortDoctors(HttpServletRequest req, List<Doctor> doctors) {
        SortDoctor sort = SortDoctor.valueOf(req.getParameter("sort"));
        switch (sort) {
            case ASC:
                doctors.sort(Comparator.comparing(Doctor::getName));
                break;
            case DESC:
                doctors.sort((o1, o2) -> o2.getName().compareTo(o1.getName()));
                break;
            case CATEGORY:
                doctors.sort(Comparator.comparing(o -> o.getDoctorSpecialisationName().name()));
                break;
            case PATIENTS_AMOUNT:
                sortByPatients(req, doctors);
                break;
        }
    }

    private void sortByPatients(HttpServletRequest req, List<Doctor> doctors) {
        List<Patient> patients = (List<Patient>) req.getSession(false).getAttribute(PATIENTS.getAttribute());
        HashMap<Integer, Integer> map = new HashMap<>();
        patients.forEach(p ->
                map.put(p.getDoctorId(), map.getOrDefault(p.getDoctorId(), 0) + 1));
        doctors.sort((o1, o2) -> {
            Integer o1Count = map.getOrDefault(o1.getId(), 0);
            return o1Count.compareTo(map.getOrDefault(o2.getId(), 0));
        });
    }

    private void sortPatients(HttpServletRequest req, List<Patient> patients) {
        SortPatient sort = SortPatient.valueOf(req.getParameter("sort"));
        switch (sort) {
            case ASC:
                patients.sort(Comparator.comparing(Patient::getName));
                break;
            case DESC:
                patients.sort((o1, o2) -> o2.getName().compareTo(o1.getName()));
                break;
            case BIRTH_ASC:
                patients.sort(Comparator.comparing(Patient::getBirth));
                break;
            case BIRTH_DESC:
                patients.sort((o1, o2) -> o2.getBirth().compareTo(o1.getBirth()));
                break;
        }
    }


}
