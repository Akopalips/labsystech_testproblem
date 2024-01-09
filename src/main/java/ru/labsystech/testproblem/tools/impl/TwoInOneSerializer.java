package ru.labsystech.testproblem.tools.impl;

import java.util.List;

/**
 * @author tuspring
 */
public class TwoInOneSerializer extends EazySerializer{//todo: если рядом стоит два числа и оба меньше 127, второе разворачиваем и складываем с первым
    @Override
    public String serialize(List<Integer> input) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int position = 0; position < input.size(); position++){
            if (areTwoSmallNumbersNearby(input, position)){ //если рядом стоит два числа и оба меньше 127
                byte first = (byte)input.indexOf(position);
                byte second = (byte)(127 - input.indexOf(position));

            } else {
                stringBuilder.append(intToASCIIString(input.indexOf(position)));
                stringBuilder.append((char) 32);
            }
        }
        return stringBuilder.toString();
    }

    private static boolean areTwoSmallNumbersNearby(List<Integer> input, int position) {
        return position != input.size() - 1 &&
            input.indexOf(position) < 127 && input.indexOf(position + 1) < 124;
    }
}
