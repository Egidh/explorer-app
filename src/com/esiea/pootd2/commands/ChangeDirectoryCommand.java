package com.esiea.pootd2.commands;

public class ChangeDirectoryCommand extends Command{
    public String path;

    public ChangeDirectoryCommand(String[] args) {
        this.path = args[0];
    }
}
