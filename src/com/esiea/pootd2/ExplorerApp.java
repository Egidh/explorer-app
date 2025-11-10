package com.esiea.pootd2;

import com.esiea.pootd2.models.*;

public class ExplorerApp {
    public static void main(String[] args) {
        FolderInode root = new FolderInode("/");

        FolderInode tmpFolder = new FolderInode("tmp/");
        root.addInode(tmpFolder);

        FolderInode homeFolder = new FolderInode("home/");
        root.addInode(homeFolder);

        FileInode helloFile = new FileInode("hello.txt");
        helloFile.addContent("Hello !\n");

        homeFolder.addInode(helloFile);

        System.out.print(homeFolder.getChildren().get(0).getContent());
        System.out.println(homeFolder.getContent());
    }   
}