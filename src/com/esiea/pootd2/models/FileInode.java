package com.esiea.pootd2.models;

public class FileInode extends Inode{
    private String content;
    int size;

    public FileInode(String name) {
        super(name);
        content = "";
        size = 0;
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
