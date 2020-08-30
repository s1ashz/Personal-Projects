package com.slash.shapedrawer.commands;

import com.slash.shapedrawer.constans.ConstantManager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CommandArguments implements ConstantManager {

    private String rawCommand;
    private String[] rawCommandArgs;

    private AvailableCommands commandType;
    private List<String> commandPoints;

    public CommandArguments(String rawCommand) {
        this.rawCommand = rawCommand;
        createCommandArgs();
    }

    private void createCommandArgs() {
        splitAndSetCommandArgs();
        setCommandType();
        setCommandPoints();
    }

    private void splitAndSetCommandArgs() {
        rawCommandArgs = rawCommand.split(USER_INPUT_SEPARATOR);
    }

    private void setCommandType() {
        commandType = AvailableCommands.getCommandWithValue( rawCommandArgs[0].toLowerCase() );
    }

    private void setCommandPoints() {
        commandPoints = getPointsFromCommandArgs();
    }

    private List<String> getPointsFromCommandArgs() {
        List<String> pointsArgs = new ArrayList<>();
        for ( String point : Arrays.asList(rawCommandArgs).subList(1, rawCommandArgs.length) ) {
            pointsArgs.add(point);
        }
        return pointsArgs;
    }

    public AvailableCommands getCommandType() {
        return commandType;
    }

    public List<String> getCommandPoints() {
        return commandPoints;
    }
}
