package ru.labsystech.testproblem.tools.impl;

import java.util.ArrayList;
import java.util.List;

import ru.labsystech.testproblem.tools.Deserializer;

/**
 * @author tuspring
 */
public class EazyDeserializer implements Deserializer {

    @Override
    public List<Integer> deserialize(String input) {
        List<Integer> result = new ArrayList<>();
        for (String eachASCIIString : input.split(Character.toString(0))) {
            result.add(ASCIIStringToInteger(eachASCIIString));
        }
        return result;
    }

    protected int ASCIIStringToInteger(String input) {
        int result = 0;
        byte pow = (byte) (input.length() - 1);
        for (byte eachSymbol : input.getBytes()) {
            result += (eachSymbol - 1) * Math.pow(127, pow--);
        }
        return result;
    }
}
