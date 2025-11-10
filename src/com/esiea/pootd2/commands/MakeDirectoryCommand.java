package com.esiea.pootd2.commands;

public class MakeDirectoryCommand extends Command{
    private String path;
    
    public MakeDirectoryCommand(String path) {
        this.path = path;
    }
}
