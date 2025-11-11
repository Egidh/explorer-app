package com.esiea.pootd2.commands;

import java.util.ArrayList;

public class ChangeDirectoryCommand extends Command{
    public String path;

    public ChangeDirectoryCommand(ArrayList<String> args) {
        this.path = args.removeFirst();
    }
}
