package com.esiea.pootd2.controllers;

import com.esiea.pootd2.commands.*;
import com.esiea.pootd2.models.*;

import java.util.ArrayList;

public class ExplorerController implements IExplorerController{
    private FolderInode currentFolder;

    public ExplorerController(FolderInode node) {
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

	public Inode getCurrentFolder() {
		return currentFolder;
	}

    private Inode getInodeFromPath(String path) {
        if(path.equals("")) return currentFolder;

        int index = 0;
        int endOfChunkIndex = -1;

        FolderInode folder = this.currentFolder;
        String pathChunk = null;
        boolean foundFolder = false;
        
        // Case where it is a full path
        endOfChunkIndex = path.indexOf("/", index);
        // Get back to "/"
        if (endOfChunkIndex == 0) {
            while (!folder.getName().equals("/")) folder = folder.getParent();
            index++;
        }
        else endOfChunkIndex = -1;
        
        while (index < path.length()) {
            foundFolder = false;
            endOfChunkIndex = path.indexOf("/", index);

            // If path is not ending with "/":
            if (endOfChunkIndex == -1)
            {
                pathChunk = path.substring(index, path.length());

                ArrayList<Inode> children = folder.getChildren(); // Go through current Inode to find correct folder
                for (Inode child : children) {
                    if (child.getName().equals(pathChunk) || child.getName().equals(pathChunk + "/")) // Check if there is a corresponding file OR folder
                        return child;
                }
                return null;
            }
            
            // Not at the end of the path:
            pathChunk = path.substring(index, endOfChunkIndex + 1);
            index += pathChunk.length();

            // Substitution
            if (pathChunk.equals("../")) {
                folder = folder.getParent();
                foundFolder = true;
                continue;
            }
            if (pathChunk.equals("./")) {
                if (index >= path.length())
                    return currentFolder;
                else {
                    foundFolder = true;
                    continue;
                }
            }

            ArrayList<Inode> children = folder.getChildren();   // Go through current Inode 
            for (Inode child : children) {
                if (child.getName().equals(pathChunk)) {        // Check if we can continue down the path
                    folder = (FolderInode)child;
                    foundFolder = true;
                }  
            }

            if (!foundFolder)
                return null;
        }
            
        return (foundFolder)? folder : null;
    }

    private String getChildrenFromPath(String path) {
        Inode target;

        if (path == null)
            target = currentFolder;
        else
            target = getInodeFromPath(path);

        // In case no inode is found
        if (target == null) return "No such file or folder: " + path; 

        // In case the inode is a file
        if (target instanceof FileInode) return target.getName();

        // In case the inode is a folder
        else {
            FolderInode folder = (FolderInode)target;
            return folder.getContent();
        }
    }

    private String doCommand(ListCommand cmd) {
        if (cmd.paths == null) return getChildrenFromPath(null) + "\n";
        if (cmd.paths.length < 2) return getChildrenFromPath(cmd.paths[0]) + "\n";

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

            int endOfPathIndex = path.lastIndexOf("/", path.length() - 2);
            String parentFolderPath = "";
            if (endOfPathIndex != -1) parentFolderPath = path.substring(0, endOfPathIndex);

            int folderNamePathIndex = path.lastIndexOf("/", path.length() - 2);
            String folderName = "";
            if (folderNamePathIndex != -1) folderName = path.substring(folderNamePathIndex + 1);
            else folderName = path;

            FolderInode parentFolder = (FolderInode)getInodeFromPath(parentFolderPath);
            if (parentFolder == null) {
                output += "Invalid path: " + parentFolderPath + "\n";
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
                return "Invalid file name: " + path + "\n"; 
            }

            int endOfPathIndex = path.lastIndexOf("/");
            String parentFolderPath = "";
            if (endOfPathIndex != -1) parentFolderPath = path.substring(0, endOfPathIndex);

            int fileNamePathIndex = path.lastIndexOf("/");
            String fileName = "";
            if (fileNamePathIndex != -1) fileName = path.substring(fileNamePathIndex + 1);
            else fileName = path;

            FolderInode parentFolder = (FolderInode)getInodeFromPath(parentFolderPath);
            if (parentFolder == null) {
                output += "Invalid path: " + parentFolderPath + "\n";
                continue;
            }

            FileInode newFile = new FileInode(fileName);
            parentFolder.addInode(newFile);
        }

        return output;
    }

    private String doCommand(ChangeDirectoryCommand cmd) {
        String output = "";
        String targetPath = cmd.path;

        if (!cmd.path.endsWith("/")) {
            targetPath += "/";
        }

        Inode targetFolder = getInodeFromPath(targetPath);
        if (targetFolder == null) {
            output += "Invalid path: " + targetPath + "\n";
        }
        else if (targetFolder instanceof FolderInode) {
            this.currentFolder = (FolderInode)targetFolder;
        }
        else {
            output += cmd.path + " is not a folder\n";
        }

        return output;
    }

    private String doCommand(ErrorCommand cmd) {
        return "Unknown command: " + cmd.getCommand() + "\n";
    }
}
