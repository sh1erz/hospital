package controller.command;

import controller.command.impl.AddDoctorCommand;
import controller.command.impl.AddPatientCommand;
import controller.command.impl.AuthorizeCommand;

public enum CommandList {

    AUTHORISATION(new AuthorizeCommand()),
    ADD_PATIENT(new AddPatientCommand()),
    ADD_DOCTOR(new AddDoctorCommand());



    CommandList(Command command) {
        this.command = command;
    }

    private final Command command;


    public Command getCommand(){
        return command;
    }
}
