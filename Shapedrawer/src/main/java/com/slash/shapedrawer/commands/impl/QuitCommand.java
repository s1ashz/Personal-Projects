package com.slash.shapedrawer.commands.impl;

import com.slash.shapedrawer.commands.AbstractCommand;
import com.slash.shapedrawer.commands.CommandArguments;

class QuitCommand extends AbstractCommand {

    public QuitCommand(CommandArguments commandArguments) {
        super(commandArguments);
    }

    @Override
    public void execute() {
        System.exit(0);
    }

}
