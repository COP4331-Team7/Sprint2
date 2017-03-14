package com.team7.model.entity;


import java.lang.reflect.Array;
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

    public void raiseCommand(String commandString) {
        for (int i=0; i < commands.size(); i++) {
            if (commandString.equals(commands.get(i).getCommandString())) {
                if (i!=0) {
                    Command temp = commands.get(i);
                    commands.set(i, commands.get(i-1));
                    commands.set(i-1, temp);
                }
            }
        }
    }

    public void lowerCommand(String commandString) {
        for (int i=0; i < commands.size(); i++) {
            if (commandString.equals(commands.get(i).getCommandString())) {
                if (i!=commands.size()-1) {
                    Command temp = commands.get(i);
                    commands.set(i, commands.get(i+1));
                    commands.set(i+1, temp);
                }
            }
        }
    }


    public Command get(int index) {
        return commands.get(index);
    }

    public int getSize() {
        return commands.size();
    }

    public ArrayList<Command> getCommandsList() {
        return commands;

    }

    public void cancelCommands() {
        for(int i = 0; i < commands.size(); i++) {
            this.commands.remove(i);
        }
    }


}


