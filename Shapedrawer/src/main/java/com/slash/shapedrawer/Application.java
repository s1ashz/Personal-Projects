package com.slash.shapedrawer;

import com.slash.shapedrawer.commands.AvailableCommands;
import com.slash.shapedrawer.commands.Command;
import com.slash.shapedrawer.commands.CommandArguments;
import com.slash.shapedrawer.commands.impl.CommandFactory;
import com.slash.shapedrawer.exceptions.ShapeDrawerException;
import com.slash.shapedrawer.validators.UserInputValidator;

import java.util.Scanner;

public class Application {

    private Scanner scanner;

    public void Start() {
        printAnnouncer();
        printAvailableCommands();
        printEnjoyMessage();
        try (Scanner scanner = new Scanner(System.in)) {
            while (true) {
                launchApp(scanner);
            }
        } catch (Exception e) {
            if ( null != scanner ) {
                scanner.close();
            }
        }
    }

    private void printAnnouncer() {
        System.out.println("Welcome to Shape Drawer");
    }

    private void printAvailableCommands() {
        System.out.println("Available Commands Are: ");
        System.out.println("" +
                "Create Canvas: " + AvailableCommands.CREATE_CANVAS_COMMAND.getValue() + "\n" +
                "Clear Canvas: " + AvailableCommands.CLEAR_CANVAS_COMMAND.getValue() + "\n" +
                "Draw Line: " + AvailableCommands.SHAPE_LINE_COMMAND.getValue() + "\n" +
                "Draw Quadrilateral: " + AvailableCommands.SHAPE_QUADRILATERAL_COMMAND.getValue() + "\n" +
                "Quit Application: " + AvailableCommands.QUIT_COMMAND.getValue());
    }

    private void printEnjoyMessage() {
        System.out.println("******** Have Fun ********\n");
    }

    private void launchApp(Scanner scanner) {
        System.out.print("\n\nEnter command: ");
        String inputString = scanner. nextLine();

        try {
            UserInputValidator validator = new UserInputValidator();
            validator.validateUserInput(inputString);
            CommandArguments commandArguments = new CommandArguments(inputString);

            CommandFactory commandFactory = new CommandFactory();
            Command command = commandFactory.getCommand(commandArguments);
            command.execute();
        } catch (ShapeDrawerException e) {
            printErrorMessage(e);
        }

    }

    private void printErrorMessage(ShapeDrawerException e) {
        System.err.println(e.getMessage());
        try {
            Thread.sleep(500);
            printAvailableCommands();
        } catch (InterruptedException e1) {
            e1.printStackTrace();
        }
    }

}

