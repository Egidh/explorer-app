package com.esiea.pootd2.models;

import java.util.Random;

public class FileInode extends Inode{
    int size;

    public FileInode(String name) {
        super(name);

        Random rnd = new Random();
        size = rnd.nextInt(1000000);
    }

    @Override
    public int getSize() {
        return size;
    }
}
