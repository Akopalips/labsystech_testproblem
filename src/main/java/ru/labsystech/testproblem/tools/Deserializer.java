package ru.labsystech.testproblem.tools;

import java.util.List;

/**
 * @author tuspring
 */
public interface Deserializer {
    List<Integer> deserialize(String input);
}
