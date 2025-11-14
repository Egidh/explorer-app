package com.esiea.pootd2.parser;

import com.esiea.pootd2.commands.*;
import java.util.Arrays;

public class UnixLikeCommandParser implements ICommandParser{
    public UnixLikeCommandParser() {

    }

    private static String[] splitArguments(String args) {
        String[] splittedArgs;
        splittedArgs = args.trim().split("\s");

        return splittedArgs;
    }

    private static Command mapCommand(String cmd, String[] args) {
        switch (cmd) {
            case "ls":
                return new ListCommand(args);
            case "cd":
                return new ChangeDirectoryCommand(args);
            case "mkdir":
                return new MakeDirectoryCommand(args);
            case "touch":
                return new TouchCommand(args);

            default:
                return new ErrorCommand(cmd);
        }
    }

    public Command parse(String line) {
        String[] parsedArgs = splitArguments(line);
        String cmd = parsedArgs[0];
        String[] args = null;
        if (parsedArgs.length > 1) {
            args = Arrays.copyOfRange(parsedArgs, 1, parsedArgs.length);
        } 
        return mapCommand(cmd, args);
    }
}
