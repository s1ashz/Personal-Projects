package com.slash.shapedrawer.commands;

import java.util.ArrayList;
import java.util.List;

public enum AvailableCommands {

    CREATE_CANVAS_COMMAND("c"),
    CLEAR_CANVAS_COMMAND("cl"),
    SHAPE_LINE_COMMAND("l"),
    SHAPE_QUADRILATERAL_COMMAND("r"),
    QUIT_COMMAND("q"),
    INVALID_COMMAND("invalid_command");

    private final String value;

    AvailableCommands(String name) {
        this.value = name;
    }

    public static AvailableCommands getCommandWithValue(String value ) {
        for ( AvailableCommands command : AvailableCommands.values()) {
            if ( command.getValue().toUpperCase().equals(value.toUpperCase())) {
                return command;
            }
        }
        return AvailableCommands.INVALID_COMMAND;
    }

    public static List<String> getAllAvailableCommands() {
        List<String> availableCommands = new ArrayList<>();
        for ( AvailableCommands shapeType : AvailableCommands.values()) {
            availableCommands.add(shapeType.getValue());
        }
        return availableCommands;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return value == null ? super.toString() : value;
    }

}
