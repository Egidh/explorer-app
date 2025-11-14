package com.esiea.pootd2.commands;

public class ErrorCommand extends Command {
    private String arg;

    public ErrorCommand(String arg) {
        this.arg = arg;
    }

    public String getCommand() {
        return arg;
    }
}
