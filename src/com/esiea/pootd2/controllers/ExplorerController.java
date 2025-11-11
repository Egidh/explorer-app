package com.esiea.pootd2.controllers;

import com.esiea.pootd2.commands.*;
import com.esiea.pootd2.models.*;

import java.util.ArrayList;

public class ExplorerController implements IExplorerController{
    private FolderInode currentFolder;

    ExplorerController(FolderInode node) {
        this.currentFolder = node;
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

        FolderInode folder = this.currentFolder;
        String pathChunk = null;
        boolean foundFolder = false;
        
        // Case where it is a full path
        endOfChunkIndex = path.indexOf("/", index);
        // Get back to "/"
        if (endOfChunkIndex == 0) while (!folder.getName().equals("/")) folder = folder.getParent();
        else endOfChunkIndex = -1;
        
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

            // Substitution
            if (pathChunk.equals("../")) {
                folder = folder.getParent();
                continue;
            }
            if (pathChunk.equals("./")) continue;

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
            if (target == null) return "No such file or folder at " + path; 

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
            String output = "";
            for (String path : cmd.paths) {
                output += path + ":\n";
                output += getChildrenFromPath(path) + "\n\n";
            }

            return output;
        }

    }

    private String doCommand(MakeDirectoryCommand cmd) {
        String output = "";

        for (String path : cmd.paths) {
            if (!path.endsWith("/")) path += "/";

            String parentFolderPath = path.substring(0, path.lastIndexOf("/", path.length() - 2));
            String folderName = path.substring(path.lastIndexOf("/", path.length() - 2) + 1);

            FolderInode parentFolder = (FolderInode)getInodeFromPath(parentFolderPath);
            if (parentFolder == null) {
                output += "Invalid path :" + parentFolderPath;
                continue;
            }

            FolderInode newFolder = new FolderInode(folderName);
            parentFolder.addInode(newFolder);
        }

        return output;
    }

    private String doCommand(TouchCommand cmd) {
        String output = "";

        for (String path : cmd.paths) {
            if (path.endsWith("/")) {
                output += "Invalid file name :" + path; 
            }

            String parentFolderPath = path.substring(0, path.lastIndexOf("/", path.length() - 2));
            String fileName = path.substring(path.lastIndexOf("/", path.length() - 2) + 1);

            FolderInode parentFolder = (FolderInode)getInodeFromPath(parentFolderPath);
            if (parentFolder == null) {
                output += "Invalid path :" + parentFolderPath;
                continue;
            }

            FileInode newFile = new FileInode(fileName);
            parentFolder.addInode(newFile);
        }

        return output;
    }

    private String doCommand(ChangeDirectoryCommand cmd) {
        return null;
    }

    private String doCommand(ErrorCommand cmd) {
        return null;
    }
}
