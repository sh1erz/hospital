package controller.command;

import controller.command.impl.AuthorizeCommand;

public enum CommandList {
    AUTHORISATION(new AuthorizeCommand());

    CommandList(Command command) {
        this.command = command;
    }

    private Command command;

    public Command getCommand(){
        return command;
    }
}
