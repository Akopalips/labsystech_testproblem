package ru.labsystech.testproblem.tools.impl;


import java.util.List;

import ru.labsystech.testproblem.tools.Serializer;

/**
 * @author tuspring
 */
public class EazySerializer implements Serializer {

    @Override
    public String serialize(List<Integer> input) {
        StringBuilder stringBuilder = new StringBuilder();
        for (Integer eachNumber : input){
            stringBuilder.append(intToASCIIString(eachNumber));
            stringBuilder.append((char)32);
        }
        return stringBuilder.toString();
    }

    protected String intToASCIIString(int input){
        return intToASCIIString(input, new StringBuilder(2));
    }

    protected String intToASCIIString(int input, StringBuilder stringBuilder){
        byte reminder = (byte)(input < 32 ? input : input % 127 + 1);
        int wholePart = input / 127;

        if (wholePart > 0){
            intToASCIIString(wholePart, stringBuilder);
        }

        stringBuilder.append(intToASCIIChar(reminder));
        return stringBuilder.toString();
    }

    protected char intToASCIIChar(int input){
        if (input > 128 || input < 0){
            throw new IllegalArgumentException("Input char does not match ASCII table:" + input + '!');
        }
        return (char) input;
    }

}
