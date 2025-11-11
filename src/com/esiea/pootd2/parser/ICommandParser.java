package com.esiea.pootd2.parser;

import com.esiea.pootd2.commands.*;

public interface ICommandParser {
    public Command parse(String args);
}
