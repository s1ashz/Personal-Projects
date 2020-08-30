package com.slash.shapedrawer.commands.impl;

import com.slash.shapedrawer.commands.AbstractCommand;
import com.slash.shapedrawer.commands.CommandArguments;
import com.slash.shapedrawer.exceptions.ShapeDrawerException;

class ClearCanvasCommand extends AbstractCommand {

    public ClearCanvasCommand(CommandArguments commandArguments) {
        super(commandArguments);
    }

    @Override
    public void execute() throws ShapeDrawerException {
        getCanvasService().clearCanvas();
    }

}
