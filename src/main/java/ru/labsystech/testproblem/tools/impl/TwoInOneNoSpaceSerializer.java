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

        for (int position = 0; position < inputSize; position++) {
            currentNumber = input.get(position);
            if (isPossibleToCompressTwoInOne(input, inputSize, currentNumber, position)) {
                appendTwoSmallNumbersAsOne(input, result, (char) currentNumber, position);
                position++;
            } else {
                result.append((char) (byte)(currentNumber % 128))
                    .append((char) (byte)(currentNumber / 128));
            }
        }
        return result.toString();
    }

    private static void appendTwoSmallNumbersAsOne(List<Integer> input, StringBuilder result, char currentNumber, int position) {
        int nextNumber = input.get(position + 1);
        result.append(currentNumber).append((char) (128 - nextNumber));
    }

    private static boolean isPossibleToCompressTwoInOne(List<Integer> input, int inputSize, int currentNumber, int position) {
        return position < inputSize - 1 &&
            currentNumber < 128 && (input.get(position + 1)) < 125;
    }
}
