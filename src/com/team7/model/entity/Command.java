package com.team7.model.entity;


public class Command {

    private String commandString;
    private int wait = 0; // ticks to wait until we can execute


    public Command() {
        commandString = "";
    }

    public Command(String string) {
        commandString = string;
        wait = 0;

        if(commandString.contains("MAKE BASE")){
            wait += 0;  // todo:change
        }


    }

    public Command(String string, int wait){
        commandString = string;
        this.wait = wait;
    }

    public String getCommandString() {
        return commandString;
    }

    public void setCommandString(String commandString) {
        this.commandString = commandString;

    }

    public void setWait(int wait) {
        this.wait = wait;
    }

    public int getWait() {
        return  wait;
    }
    public void decrementWait() {
        wait--;
    }

}
