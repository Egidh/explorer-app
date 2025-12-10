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

    public ArrayList<Inode> getChildren() {
        return this.children;
    }

    @Override
    public int getSize() {
        return size;
    }
}
