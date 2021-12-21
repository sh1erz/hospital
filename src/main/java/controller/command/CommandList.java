package controller.command;

import controller.command.impl.*;

public enum CommandList {

    AUTHORISATION(new AuthorizeCommand()),
    ADD_PATIENT(new AddPatientCommand()),
    ADD_DOCTOR(new AddDoctorCommand()),
    ADD_MEDCARD_RECORD(new AddMedcardRecordCommand()),
    SET_DIAGNOSIS(new SetDiagnosisCommand()),
    CHOOSE_PATIENT(new ChoosePatientCommand()),
    SET_PERFORMER(new SetPerformerCommand());



    CommandList(Command command) {
        this.command = command;
    }

    private final Command command;


    public Command getCommand(){
        return command;
    }
}
