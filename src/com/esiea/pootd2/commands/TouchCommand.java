package com.esiea.pootd2.commands;

import java.util.ArrayList;

public class TouchCommand extends Command{
    public ArrayList<String> paths;

    public TouchCommand(ArrayList<String> args) {
        this.paths = args;
    }
}
