package com.esiea.pootd2.commands;

import java.util.ArrayList;

public class MakeDirectoryCommand extends Command{
    public ArrayList<String> paths;
    
    public MakeDirectoryCommand(ArrayList<String> args) {
        this.paths = args;
    }
}
