package com.team7.model.entity;


public class Command {

    private String commandString;
    private int wait = 0; // ticks to wait until we can execute


    public Command() {
        commandString = "";
    }

    public Command(String string) {
        commandString = string;

        if(commandString.contains("MAKE BASE"))
            wait += 5;

    }

    public String getCommandString() {
        return commandString;
    }

    public void setCommandString(String commandString) {
        this.commandString = commandString;

    }

    public int getWait() {
        return  wait;
    }
    public void decrementWait() {
        wait--;
    }

}
