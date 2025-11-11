package com.esiea.pootd2.parser;

import com.esiea.pootd2.commands.*;
import java.util.ArrayList;
import java.util.Arrays;

public class UnixLikeCommandParser implements ICommandParser{
    public UnixLikeCommandParser() {

    }

    private static ArrayList<String> splitArguments(String args) {
        ArrayList<String> splittedArgs;
        splittedArgs = new ArrayList<String>(Arrays.asList(args.split("\s")));

        return splittedArgs;
    }

    private static Command mapCommand(String cmd, ArrayList<String> args) {
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
        ArrayList<String> parsedArgs = splitArguments(args);
        String cmd = parsedArgs.removeFirst();
        mapCommand(cmd, parsedArgs);
        return null;
    }
}
