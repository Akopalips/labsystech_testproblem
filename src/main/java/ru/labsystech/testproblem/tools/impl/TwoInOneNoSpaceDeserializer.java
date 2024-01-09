package ru.labsystech.testproblem.tools.impl;

import java.util.ArrayList;
import java.util.List;

import ru.labsystech.testproblem.tools.Deserializer;

/**
 * @author tuspring
 */
public class TwoInOneNoSpaceDeserializer implements Deserializer {

    @Override
    public List<Integer> deserialize(String input) {
        List<Integer> result = new ArrayList<>();
        for (int position = 0; position < input.length() - 1; position += 2) {
            if ((byte)(input.charAt(position + 1)) > 2){
                result.add((int)input.charAt(position));
                result.add(128 - (byte)input.charAt(position + 1));
            } else {
                result.add(input.charAt(position) + input.charAt(position + 1) * 128);
            }
        }
        return result;
    }
}
