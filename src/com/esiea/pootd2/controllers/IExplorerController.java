package com.esiea.pootd2.controllers;

import com.esiea.pootd2.commands.*;
import com.esiea.pootd2.models.Inode;

public interface IExplorerController {
    public String executeCommand(Command cmd);
    public Inode getCurrentFolder();    
}
