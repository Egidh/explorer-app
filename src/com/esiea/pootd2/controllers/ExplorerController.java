package com.esiea.pootd2.controllers;

import com.esiea.pootd2.commands.*;
import com.esiea.pootd2.models.*;

import java.io.File;
import java.util.ArrayList;

public class ExplorerController implements IExplorerController{
    private FolderInode currentInode;

    ExplorerController(FolderInode node) {
        this.currentInode = node;
    }

    public String executeCommand(Command cmd) {
        switch (cmd.getClass().getSimpleName()) 
        {
            case "ListCommand":
                return doCommand((ListCommand) cmd);

            case "MakeDirectoryCommand":
                return doCommand((MakeDirectoryCommand) cmd);

            case "ChangeDirectoryCommand":
                return doCommand((ChangeDirectoryCommand) cmd);

            case "TouchCommand":
                return doCommand((TouchCommand) cmd);

            default:
                return doCommand((ErrorCommand) cmd);
        }
    }

    private Inode getInodeFromPath(String path) {
        int index = 0;
        int endOfChunkIndex = -1;

        FolderInode folder = this.currentInode;
        String pathChunk = null;
        boolean foundFolder = false;
        
        while (index < path.length()) {
            endOfChunkIndex = path.indexOf("/", index);

            // If path is not ending with "/":
            if (endOfChunkIndex == -1)
            {
                pathChunk = path.substring(index, path.length() - 1);

                ArrayList<Inode> children = folder.getChildren(); // Go through current Inode to find correct folder
                for (Inode child : children) {
                    if (child.getName().equals(pathChunk) || child.getName().equals(pathChunk + "/")); // Check if there is a corresponding file OR folder
                        return child;
                }
            }
            
            // Not at the end of the path:
            pathChunk = path.substring(index, endOfChunkIndex);

            index += pathChunk.length();

            ArrayList<Inode> children = folder.getChildren();   // Go through current Inode 
                for (Inode child : children) {
                    if (child.getName().equals(pathChunk)) {    // Check if we can continue down the path
                        folder = (FolderInode)child;
                        foundFolder = true;
                    }  
                }
            
            if (foundFolder) foundFolder = false;
            else return null;
        }
            
        return null;
    }

    private String getChildrenFromPath(String path) {
        Inode target;

        target = getInodeFromPath(path);

            // In case no inode is found
            if (target == null) doCommand(new ErrorCommand("No file or folder at " + path)); 

            // In case the inode is a file
            if (target instanceof FileInode) return target.getName();

            // In case the inode is a folder
            else {
                FolderInode folder = (FolderInode)target;
                return folder.getContent();
            }
    }

    private String doCommand(ListCommand cmd) {
         

        if (cmd.paths.length == 1) return getChildrenFromPath(cmd.paths[0]);

        else {
            for (String path : cmd.paths)
        }

    }

    private String doCommand(MakeDirectoryCommand cmd) {
        return null;
    }

    private String doCommand(TouchCommand cmd) {
        return null;
    }

    private String doCommand(ChangeDirectoryCommand cmd) {
        return null;
    }

    private String doCommand(ErrorCommand cmd) {
        return null;
    }
}
