package com.slash.shapedrawer.commands;

import java.util.ArrayList;
import java.util.List;

public class CommandNumbers {

    private Integer firstPoint;
    private Integer secondPoint;
    private Integer thirdPoint;
    private Integer fourthPoint;

    private List<Integer> commandNumbers;

    public CommandNumbers() {
        commandNumbers = new ArrayList<>();
    }

    public Integer getFirstPoint() {
        return firstPoint;
    }

    public Integer getSecondPoint() {
        return secondPoint;
    }

    public Integer getThirdPoint() {
        return thirdPoint;
    }

    public Integer getFourthPoint() {
        return fourthPoint;
    }

    public List<Integer> getCommandNumbers() {
        return commandNumbers;
    }

    public void setCommandPoints(List<String> commandPoints) {
        for (String points : commandPoints) {
            commandNumbers.add(Integer.valueOf(points));
        }
        updateNumbers();
    }

    private void updateNumbers() {
        if(commandNumbers.size()>0){
            firstPoint = commandNumbers.get(0);
        }
        if(commandNumbers.size()>1){
            secondPoint = commandNumbers.get(1);
        }
        if(commandNumbers.size()>2){
            thirdPoint = commandNumbers.get(2);
        }
        if(commandNumbers.size()>3){
            fourthPoint = commandNumbers.get(3);
        }
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("CommandNumbers{");
        sb.append("firstPoint=").append(firstPoint);
        sb.append(", secondPoint=").append(secondPoint);
        sb.append(", thirdPoint=").append(thirdPoint);
        sb.append(", fourthPoint=").append(fourthPoint);
        sb.append(", commandNumbers=").append(commandNumbers);
        sb.append('}');
        return sb.toString();
    }
}
