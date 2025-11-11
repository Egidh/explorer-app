package com.esiea.pootd2.commands;

import java.util.ArrayList;

public class ListCommand extends Command{
    public ArrayList<String> paths;

    public ListCommand(ArrayList<String> args) {
        this.paths = args;
    }
}
