package com.slash.shapedrawer.commands;

import com.slash.shapedrawer.canvas.service.CanvasService;
import com.slash.shapedrawer.canvas.service.CanvasServiceImpl;

public abstract class AbstractCommand implements Command {

    private CommandArguments commandArguments;
    private CanvasService canvasService;

    public AbstractCommand(CommandArguments commandArguments ) {
        this.commandArguments = commandArguments;
        canvasService = CanvasServiceImpl.getInstance();
    }

    protected CommandArguments getCommandArguments() {
        return commandArguments;
    }

    protected CanvasService getCanvasService() {
        return canvasService;
    }


}
