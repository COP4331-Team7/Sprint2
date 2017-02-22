package com.team7.model.entity;


public class Command {

    private String commandString;

    public Command() {
        commandString = "";
    }

    public Command(String string) {
        commandString = string;
    }

    public String getCommandString() {
        return commandString;
    }

    public void setCommandString(String commandString) {
        this.commandString = commandString;
    }

}
