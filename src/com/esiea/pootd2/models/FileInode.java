package com.esiea.pootd2.models;

import java.util.Random;

public class FileInode extends Inode{
    private String content;
    int size;

    public FileInode(String name) {
        super(name);
        content = "";

        Random rnd = new Random();
        size = rnd.nextInt(1000000);
    }

    public String getName() {
        return super.getName();
    }

    public String getContent() {
        return this.content;
    }

    public void addContent(String content) {
        this.content += content;
    }

    @Override
    public int getSize() {
        return size;
    }
}
