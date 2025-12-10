package com.esiea.pootd2;

import com.esiea.pootd2.models.*;
import com.esiea.pootd2.controllers.*;
import com.esiea.pootd2.interfaces.*;

public class ExplorerApp {
    public static void main(String[] args) {
        FolderInode root = new FolderInode("/");

        FolderInode tmpFolder = new FolderInode("tmp/");
        root.addInode(tmpFolder);

        FolderInode homeFolder = new FolderInode("home/");
        root.addInode(homeFolder);

        FileInode helloFile = new FileInode("hello.txt");

        homeFolder.addInode(helloFile);

        IExplorerController explorer = new ExplorerController(root);
        IUserInterface tui = new TextInterface(explorer);
        tui.run();
    }   
}