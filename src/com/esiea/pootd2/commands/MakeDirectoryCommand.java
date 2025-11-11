package com.esiea.pootd2.commands;

public class MakeDirectoryCommand extends Command{
    public String[] paths;
    
    public MakeDirectoryCommand(String[] args) {
        this.paths = args;
    }
}
