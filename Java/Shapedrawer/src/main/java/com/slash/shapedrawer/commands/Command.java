package com.slash.shapedrawer.commands;

import com.slash.shapedrawer.exceptions.CanvasException;
import com.slash.shapedrawer.exceptions.ShapeDrawerException;

public interface Command {

    void execute() throws ShapeDrawerException;
}
