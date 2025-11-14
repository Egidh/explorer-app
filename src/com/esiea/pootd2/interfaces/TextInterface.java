package com.esiea.pootd2.interfaces;

import java.util.Scanner;
import com.esiea.pootd2.controllers.*;
import com.esiea.pootd2.parser.*;
import com.esiea.pootd2.models.Inode;

public class TextInterface implements IUserInterface{
    private IExplorerController controller;
    private ICommandParser parser;

    public TextInterface(IExplorerController controller) {
        this.controller = controller;
        this.parser = new UnixLikeCommandParser();
    }

    String output = "";

    public void run() {
        Scanner scan = new Scanner(System.in);
        String userInput = null;
        
        while (true) {
            System.out.print(controller.getCurrentFolder().getName() + "-$ ");
            userInput = scan.nextLine();

            if (userInput.equals("exit"))
                break;
            
            output = controller.executeCommand(this.parser.parse(userInput));
            System.out.print(output);
        }
        
        scan.close();
    }
}
