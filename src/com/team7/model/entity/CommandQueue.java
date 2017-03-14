package com.team7.model.entity;


import java.util.ArrayList;

public class CommandQueue {

    private ArrayList<Command> commands;

    public CommandQueue() {
        commands = new ArrayList<Command>();
    }

    public void queueCommand(Command command) {
        commands.add(command);
    }

    public void removeCommand() {
        if(commands.size() == 0)
            return;
        else
            commands.remove(0);
    }

    public void removeCommandByString(String commandString) {
        for (int i = 0; i < commands.size(); i++) {
            if (commandString.equals(commands.get(i).getCommandString())) {
                commands.remove(i);
            }
        }
    }


    public Command get(int index) {
        return commands.get(index);
    }

    public int getSize() {
        return commands.size();
    }


}


