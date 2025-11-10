package com.esiea.pootd2.commands;

public class TouchCommand extends Command{
    private String path;

    public TouchCommand(String path) {
        this.path = path;
    }
}
