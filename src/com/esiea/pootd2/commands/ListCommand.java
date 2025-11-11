package com.esiea.pootd2.commands;

public class ListCommand extends Command{
    public String[] paths;

    public ListCommand(String[] args) {
        this.paths = args;
    }
}
