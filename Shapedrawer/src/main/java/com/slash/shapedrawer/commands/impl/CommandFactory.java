package com.slash.shapedrawer.commands.impl;

import com.slash.shapedrawer.commands.Command;
import com.slash.shapedrawer.commands.CommandArguments;

public class CommandFactory {

    public Command getCommand(CommandArguments commandArguments) {

        Command enteredCommand = null;

        switch(commandArguments.getCommandType()) {
            case QUIT_COMMAND:
                enteredCommand = new QuitCommand(commandArguments);
                break;
            case CREATE_CANVAS_COMMAND:
                enteredCommand = new CreateCanvasCommand(commandArguments);
                break;
            case CLEAR_CANVAS_COMMAND:
                enteredCommand = new ClearCanvasCommand(commandArguments);
                break;
            case SHAPE_LINE_COMMAND:
                enteredCommand = new CreateShapeCommand(commandArguments);
                break;
            case SHAPE_QUADRILATERAL_COMMAND:
                enteredCommand = new CreateShapeCommand(commandArguments);
                break;
            default:
                enteredCommand = new CreateCanvasCommand(commandArguments);
        }
        return enteredCommand;
    }

}
