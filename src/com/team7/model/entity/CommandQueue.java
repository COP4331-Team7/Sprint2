package com.team7.model.entity;


import java.util.ArrayList;

public class CommandQueue {

    private ArrayList<Command> commands;

    public CommandQueue() {
        commands = new ArrayList<Command>();
    }

    public void addCommand(Command command) {
        commands.add(command);
    }

    public void removeCommandByString(String commandString) {
        for (int i = 0; i < commands.size(); i++) {
            if (commandString.equals(commands.get(i).getCommandString())) {
                commands.remove(i);
            }
        }
    }



}


