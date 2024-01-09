package ru.labsystech.testproblem.tools.impl;

import java.util.List;

import ru.labsystech.testproblem.tools.Serializer;

/**
 * @author tuspring
 */
public class TwoInOneNoSpaceSerializer implements Serializer {

    public String serialize(List<Integer> input) {
        StringBuilder result = new StringBuilder();
        int inputSize = input.size();
        int currentNumber;
        int nextNumber;
        for (int position = 0; position < inputSize; position++) {
            currentNumber = input.get(position);
            if (position < inputSize - 1 &&
                currentNumber < 128 && (nextNumber = input.get(position + 1)) < 125) {
                result.append((char) currentNumber).append((char) (128 - nextNumber));
                position++;
            } else {
                result.append((char) (byte)(currentNumber % 128))
                    .append((char) (byte)(currentNumber / 128));
            }
        }
        return result.toString();
    }
}
