package com.esiea.pootd2.parser;

import com.esiea.pootd2.commands.*;
import java.util.Arrays;

public class UnixLikeCommandParser implements ICommandParser{
    public UnixLikeCommandParser() {

    }

    private static String[] splitArguments(String args) {
        String[] splittedArgs;
        splittedArgs = args.split("\s");

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

    public Command parse(String args) {
        String[] parsedArgs = splitArguments(args);
        String cmd = parsedArgs[0];
        return mapCommand(cmd, Arrays.copyOfRange(parsedArgs, 1, parsedArgs.length));
    }
}
