package com.slash.shapedrawer.canvas.service;

import com.slash.shapedrawer.constans.ConstantManager;
import com.slash.shapedrawer.exceptions.CanvasException;
import com.slash.shapedrawer.exceptions.ShapeDrawerException;

import java.util.Arrays;
import java.util.List;

class Canvas implements ConstantManager {

    private int width;
    private int height;

    private char[][] canvas;

    private static final String LINE_SEPERATOR = System
            .getProperty("line.separator");

    public Canvas(int width, int height) {
        this.width = width;
        this.height = height;

        canvas = new char[height + 2][width + 2];
        updateConsole();
    }

    private void updateConsole() {
        System.out.println(getShapeAsString());
    }

    public void drawShape(List<LinePoints> linePoints) throws ShapeDrawerException {
        for (LinePoints line : linePoints) {
            draw(line.getPointOneX(), line.getPointOneY(), line.getPointTwoX(), line.getPointTwoY(), 'y');
        }
        updateConsole();
    }

    private void draw(int x1, int y1, int x2, int y2, char drawChar) throws ShapeDrawerException {
        try {

            if (x1 == x2) {
                // vertical line
                for (int i = y1; i <= y2; i++) {
                    canvas[i][x1] = drawChar;
                }
            } else if (y1 == y2) {
                // horizontal line
                Arrays.fill(canvas[y1], x1, x2 + 1, drawChar);
            } else {
                // we have a slope
                double slope = (double) (y2 - y1) / (double) (x2 - x1);
                for (int i = y1; i <= y2; i++) {
                    canvas[i][(int) Math.ceil(x1 + (slope * i))] = drawChar;
                }
            }
        } catch (Exception e) {
            throw new CanvasException(EXCEPTION_CANVAS_BOUNDARIES);
        }
    }

    public String getShapeAsString() {
        StringBuilder results = new StringBuilder();
        for (int vertical = 0; vertical < canvas.length; ++vertical) {
            for (int horizontal = 0; horizontal < canvas[vertical].length; horizontal++) {
                appendBorder(results, vertical, horizontal);
            }
            results.append(LINE_SEPERATOR);
        }
        return results.toString();
    }

    private void appendBorder(StringBuilder results, int vertical, int horizontal) {
        if (vertical == 0 || vertical == canvas.length - 1) {
            if (vertical == 0 && horizontal == 0) {
                results.append("     ");
            } else if (vertical == 0 && horizontal > 0 && horizontal < 10 && horizontal < canvas[vertical].length - 1) {
                results.append(horizontal + "  ");
            } else if (vertical == 0 && horizontal >= 9 && horizontal < canvas[vertical].length - 1) {
                results.append(horizontal + " ");
            } else if (vertical == canvas.length - 1 && horizontal == 0) {
                results.append("     ");
            } else if (vertical == canvas.length - 1 && horizontal < canvas[vertical].length - 1) {
                //results.append("*  ");
            }

        } else if (horizontal == 0 || horizontal == canvas[vertical].length - 1) {
            if (horizontal == 0 && vertical < 10) {
                results.append(vertical + "  | ");
            } else if (horizontal == 0 && vertical >= 9 && horizontal < canvas.length - 1) {
                results.append(vertical + " | ");
            } else if (horizontal == canvas[vertical].length - 1) {
                results.append("|");
            }

        } else {
            results.append((canvas[vertical][horizontal]) == 0 ? "-  " : "" + canvas[vertical][horizontal] + "  ");
        }
    }


    public void clearCanvas() {
        canvas = new char[height + 2][width + 2];
        updateConsole();
    }

}
