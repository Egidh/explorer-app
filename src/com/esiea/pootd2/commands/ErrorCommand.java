package com.esiea.pootd2.commands;

public class ErrorCommand extends Command {
    private String arg;

    public ErrorCommand(String arg) {
        this.arg = arg;
    }

    public void printError() {
        System.err.println("Error while running: " + arg);
    }
}
