package com.esiea.pootd2.interfaces;

import java.util.Scanner;

public class TextInterface implements IUserInterface{
    public void run() {
        Scanner scan = new Scanner(System.in);
        String userInput = null;
        
        while (true) {
            System.out.print("$ ");
            userInput = scan.nextLine();

            if (userInput.equals("exit"))
                break;
        }
        
        scan.close();
    }
}
