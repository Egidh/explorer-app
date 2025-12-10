package com.esiea.pootd2.interfaces;

import java.util.Scanner;
import com.esiea.pootd2.controllers.*;
import com.esiea.pootd2.parser.*;

public class TextInterface implements IUserInterface{
    private IExplorerController controller;
    private ICommandParser parser;

    public TextInterface(IExplorerController controller) {
        this.controller = controller;
        this.parser = new UnixLikeCommandParser();
    }

    
    public void run() {
        String output = "";
        Scanner scan = new Scanner(System.in);
        String userInput = null;
        
        while (true) {
            System.out.print("$ ");
            userInput = scan.nextLine();

            if (userInput.contains("exit"))
                break;
            
            output = controller.executeCommand(this.parser.parse(userInput));
            System.out.print(output);
        }
        
        scan.close();
    }
}
