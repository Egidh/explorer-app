package com.esiea.pootd2.models;

import java.util.ArrayList;

public class FolderInode extends Inode {
    private ArrayList<Inode> children;
    private int size;

    public FolderInode(String name) {
        super(name);
        this.children = new ArrayList<>();
        this.size = 0;
    }

    public void addInode(Inode child) {
        child.parent = this;
        children.add(child);
        this.size++;
    }

    public String getName() {
        return super.getName();
    }

    public ArrayList<Inode> getChildren() {
        return this.children;
    }

    public String getContent() {
        String inodes = "\n";

        for (Inode inode : children) {
            inodes += inode.getName() + " size: " + inode.getSize() + "\n";
        }
        
        return inodes.substring(0, inodes.length() - 2); //removing last \n
    }

    @Override
    public int getSize() {
        return size;
    }
}
