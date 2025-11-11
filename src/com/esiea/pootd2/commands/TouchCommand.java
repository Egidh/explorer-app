package com.esiea.pootd2.commands;

public class TouchCommand extends Command{
    public String[] paths;

    public TouchCommand(String[] args) {
        this.paths = args;
    }
}
